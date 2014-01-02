/**
The MIT License (MIT)

Copyright (c) 2013 宇宙総姐夫 (xuechong87@gmail.com)

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

@fileOverview
@author  宇宙総姐夫 xuechong87
@version 0.1
@license The MIT License: Copyright (c) 2013 xuechong87.
**/

(function($) { 


	$.fn.StickyNotes = function(options){

		this.each(function () {

			var opts = $.extend({

				draggableIndex:2000,
				zIndex : 100,
				containment:'body',
				minHeight: 120,
				minWidth: 130,
				beforeClose:function(data){
					return true;
				},
				save:function(data){
					return true;
				},
				id:'',
				content:'',
				top:'100px',
				left:'100px',
				color:'',/*'red','blue','orange','pink','yellow','green'*/
				saveOnCreate:false

			}, options);

			init.call(this,opts);
		});

		return this;
	};


	function init(settings){
		
		var _this = $(this);
		_this.attr('sticky_id',settings.id);
		_this.addClass('sticky_notes');

		var regPx = /^\d+px$/;
		if(!regPx.test(_this.css('top'))){
			_this.css('top',settings.top);
		}
		if(!regPx.test(_this.css('left')) ){
			_this.css('left',settings.left);
		}

		///the warnbox
		var showWarn = function(content){
			var _warnbox = $("<div class='stiky_warn' ></div>")
					.appendTo($(settings.containment));;
			_warnbox.html(content);
			var plus = function (origin){
				return (parseInt(origin.replace('px','')) + 40) + 'px';
			};
			_warnbox.css('left',plus(_this.css('left')));
			_warnbox.css('top',plus(_this.css('top')));	
			_warnbox.fadeIn(200);
			_warnbox.fadeOut(2500,function(){_warnbox.remove();});
			
		};
		///the warnbox

		var saveData = function(){
			if(settings.save(getData())){
				//every thing goes good
				refreshButton.hide();
			}else{
				//something wrong
				if(refreshButton){
					refreshButton.show();
				}
				showWarn('保存失败<br/>请稍后点击左上角<br/>重新尝试');
			}
		};
		
		var doClose = function (){
			if(settings.beforeClose(getData())){
				_this.fadeOut(400,function(){_this.remove();});
			}else{
				showWarn('删除失败<br/>请稍后再试');
			}
		};

		/**
		get the datas to save 
		*/
		var getData = function(){
			var datas = new Object();
			datas.content = _this.children('textarea').val();
			datas.id = _this.attr('sticky_id');
			datas.top = _this.css('top');
			datas.left = _this.css('left');
			datas.color = settings.color;
			return datas;
		};

		_this.draggable({
						zIndex: settings.draggableIndex, 
						containment: settings.containment,
						stop:saveData
					})
			.resizable({
				handles: 'all', 
				minHeight: settings.minHeight, 
				minWidth: settings.minWidth });

		////the close 
		var removeButton;
		if (_this.find(".ui-resizable-ne").length != 0){
            removeButton = $($(this).find(".ui-resizable-ne")[0]);
            removeButton.addClass('ui-icon');
            removeButton.addClass('ui-button');
			removeButton.addClass('ui-icon-closethick');
			removeButton.addClass('sticky_notes_pointer');
			removeButton.click(function(){
				doClose();
			});
			removeButton.hide();
			_this.mouseover(function(){removeButton.show();});
			_this.mouseout(function(){removeButton.hide();});
        }
        ////the close end

        ///the refresh
        var refreshButton ;
        if (_this.find(".ui-resizable-nw").length != 0){
            refreshButton = $($(this).find(".ui-resizable-nw")[0]);
            refreshButton.addClass('ui-icon');
            refreshButton.addClass('ui-button');
			refreshButton.addClass('ui-icon-arrowrefresh-1-n');
			refreshButton.addClass('sticky_notes_pointer');
			refreshButton.click(function(){
				saveData();
			});
			refreshButton.hide();	
        }
        ///the refresh end
        

        /// textarea
        if(_this.children('textarea').length<=0){
        	$("<textarea></textarea>")
        	.appendTo(_this);
        }

        var _textarea = _this.children('textarea');

        if(settings.content){
        	_textarea.val(settings.content);
        }

        var resizeText = function() {
			// configure the content proxy to be exactly like this textarea
			var contentProxy = $("#sticky_notes_content_proxy");
			
			if(contentProxy.length<=0){
				contentProxy = $("<div id='sticky_notes_content_proxy'></div>")
					.appendTo($('body'));
			}

			contentProxy
				.html( _textarea.val().replace(/\n/g, '<br>') )
				.width( _this.width());

			// we can now read the computed height off the proxy
			var contentHeight = contentProxy.height();
			
			// auto-expand, leaving room for a blank line at the bottom
			if ( contentHeight + 60 > _this.height() ) {
				_this.height(contentHeight + 60);
			}
			// IE doesn't respect height: 100% on textareas 
			if ( jQuery.browser.msie ) _this.height( contentHeight + 60 );
		};

        _textarea
        .css({overflow: 'hidden'})
        .keyup(resizeText);


		if ( jQuery.browser.msie ) {
			//_textarea.height( _this.height());
			_this.bind('resize', function() {
				_textarea.height( _this.height() - 10);
			});
		}

		_textarea.blur(function(){///save after edit content
			saveData();
		});

		resizeText();
		//////textarea

		//stack
		var compactifyZIndexes =  function () { 
			$('.sticky_notes').sort( function (a, b) {
				// first sort the elements by zIndex...
				var az = parseInt(a.style.zIndex) || 0;
				var bz = parseInt(b.style.zIndex) || 0;
				if ( az === bz ) return 0;
				else if ( az < bz ) return -1;
				else return 1;
			}).each(function(i) {
				// then assign sequential zIndexes to them.
				this.style.zIndex = settings.zIndex + i * 10;
			});
		};

		_this.mousedown(function() {
			this.style.zIndex = 9999999;
			compactifyZIndexes();
		});

		compactifyZIndexes();
		//stack end

		///color
		if(!settings.color){
			var key = (new Date().getMilliseconds())%6;
			var _colors = ['red','blue','orange','pink','yellow','green'];
			settings.color = 'sticky-' + _colors[key];
		}
		_this.addClass(settings.color);
		//color end

		if(settings.saveOnCreate){
			saveData();
		}
		
		return $(this);
		
	}
	
})(jQuery);