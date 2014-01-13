/**
The MIT License (MIT)

Copyright (c) 2013 宇宙総姐夫 xuechong87@gmail.com

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

**/
var showIcoOnHover = function (){
	if($(".tabs_icon_container").length >0 ){
		$(".tabs_icon_container").each(function(){
			var tabico = $(this);
			var getTab = function(){
				return tabico;
			};
			$(this).parent().mouseover(function(){
				getTab().show();
			}).mouseout(function(){
				getTab().hide();
			});
		});
	}
};

var initWidths = function(){
	var _totalWidth = 0;
	$(".tabs_tab").each(function(){
		_totalWidth += $(this).width() + 5;
	});
	$("#tabs_tab_parent").width(_totalWidth);

	$("#tabs_container").width($(".tabs_warp").width() - ($(".tabs_arrow").width()*2 + 8));
};

var initMoves = function(){

	var _container = $("#tabs_container");
	var _tabParent = $("#tabs_tab_parent");
	var _move = 140;

	function maxMargin(){
		return $("#tabs_container").width() - $("#tabs_tab_parent").width();
	}

	function pxToInt(input){
		return parseInt(input.substring(0,input.length-2));
	}

	if(_tabParent.width() > _container.width()){
		$(".tabs_arrow").show().css("visibility", "visible");
	}

	$(".tabs_arrow_right").click(function(){
		var margin = _tabParent.css('margin-left');
		margin = pxToInt(margin);
		margin = margin - _move;
		if(margin < maxMargin()){
			margin = maxMargin();
		}
		_tabParent.animate({marginLeft:margin+'px'},300);
	});

	$(".tabs_arrow_left").click(function(){
		var margin = _tabParent.css('margin-left');
		margin = pxToInt(margin);
		margin = margin + _move;
		if(margin > 0){
			margin = 0;
		}
		_tabParent.animate({marginLeft:margin+'px'},300);
	});
};

var initHovers = function(){

	$(".tabs_tab").mouseover(function(){
		if(!$(this).hasClass('tabs_selected')){
			$(this).addClass('tabs_hover');
		}
	}).mouseout(function(){
		$(this).removeClass('tabs_hover');
	});

	$(".tabs_arrow").mouseover(function(){
		$(this).addClass('tabs_arrow_hover');
	}).mouseout(function(){
		$(this).removeClass('tabs_arrow_hover');
	});

};