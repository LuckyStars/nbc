/*
 * jQuery jEmotion
 *
 * @version: 0.3
 * @update: 2011.11.04
 * @author: alswl
 * @website: http://log4d.com/2011/11/jemotion
 * @demo: http://lab.log4d.com/javascript/jEmotion/demo.html
 * @source: https://github.com/alswl/jEmotion
 *
 * Dual licensed under the and GPL licenses:
 *   http://www.gnu.org/licenses/gpl.html
 * 
 */

/**
	 * 定义文本图片对应关系
	 */
var smilesMaps = {
		"傲慢":"傲慢.png",
		"呲牙":"呲牙.png",
		"大笑":"大笑.png",
		"流汗":"流汗.png",
		"惊讶":"惊讶.png",
		"可爱":"可爱.png",
		"大哭":"大哭.png",
		"酷酷":"酷酷.png",
		"困了":"困了.png",
		"难过":"难过.png",
		"亲亲":"亲亲.png",
		"挑逗":"挑逗.png",
		"微笑":"微笑.png",
		"问题":"问题.png",
		"再见":"再见.png"
		};

var jEmotion = function (settings_) {
	var jemo = new Object();

	jemo.settings = settings_;
	jemo._this = $(jemo.settings.input);
	jemo.handler = $("#" + jemo.settings.handler);
	/**
	 * 插件设定
	
	settings: {
		input: null, //input textarea
		handler: null, //触发按钮
		imagePath: 'images', //图片相对路径
		converts: '' //启动转换的元素 TODO: 捕捉body会出现问题
	},*/

    /**
     * 初始化对向
     */
	jemo.init = function() {

		if (jemo.settings.handler == null || jemo.settings.imagePath == null ||
			jemo.settings.input == null) {
			alert('jEmotion load error setting.');
			return false;
		}
		jemo.bindHandlerClickEvent();
		jemo.bindEmotionClickEvent();
		$("html").click(function(){
			$(".je_emotions").fadeOut("fast");
		});
		return jemo;
	};

	jemo.textId = function(){
		return 'emo_'+jemo.settings.input;
	};

    /**
     * 插入html到页面
     */
	jemo.insertHtml = function() {
		var html = '<style>.je_emotions{display:none;width:150px; position:absolute;border:1px solid #aaa;border-top:none;z-index:9999; text-align:center;padding:3px;padding-bottom:6px;background:#fff;} .je_emotions a img{float:left;cursor:pointer;margin:1px 1px; border:#cacaca 1px solid;visibility:visible;} .je_emotions a:hover img{border:1px solid #f51d69} </style>';
		html += '<div class="je_emotions" id="'+jemo.textId()+'" >';
		for (i in smilesMaps) {
			html += '<a href="javascript:;">' +
				jemo.code2img(i) + '</a>';
		}
		html += '</div>';
		var content = $(html);
		content.appendTo($("body"));
		//$("body").append(html);
		content.show();
	};

	/*
	 * 设定表情框位置
	 */
	jemo.setPosition = function() {
		var top = jemo.handler.offset().top;
		var left = jemo.handler.offset().left;
		$('#' + jemo.textId()).css("top", parseInt(top) + "px");
		$('#' + jemo.textId()).css("left", parseInt(left) + "px");
	};

    /**
     * 绑定触发点击事件
     */
	jemo.bindHandlerClickEvent = function() {
	
		jemo.handler.live('click', function(event) {
			
			if ($('#' + jemo.textId()).size() > 0) { //如果存在就切换显示
				$('#' + jemo.textId()).toggle();
			
			} else {
				jemo.insertHtml();
			}
			jemo.setPosition();
		});
	};

    /**
     * 绑定触发点击事件
     */
	jemo.bindEmotionClickEvent = function() {
		$("#" + jemo.textId() +" img").live('click', (function() {
			jemo.insertToCursor('[' + $(this).attr('title') +']');
		}));
	};

    /**
     * 替换表情到文件名
     */
	jemo.code2html =  function(text) {
		var match = /\[([\u4e00-\u9fa5]*)\]/g; 
		var e;
		while(e = match.exec(text)){
			text = text.replace(e[0], jemo.code2img(e[1]));
		}
		return text;
	};

	/**
	 * 转换 哈哈 -> <img/>
	 */
	jemo.code2img = function(code) {
		return '<img src="' + jemo.settings.imagePath + smilesMaps[code] +
				'" title="' + code +'" />';
	};

	/**
	 * 光标处插入 //TODO 光标返回原来位置
	 */
	jemo.insertToCursor = function(text) {
		var input = $("#" + jemo.settings.input);
		var length = input.val().length;
		input.focus();
		if(typeof document.selection !="undefined") {
			document.selection.createRange().text = text; 
			document.selection.createRange().select();
		} else {
			input.val(input.val().substr(0, input[0].selectionStart) + text + 
				input.val().substring(input[0].selectionStart, length));
		}
	};

	return jemo;
};

$.fn.jEmotion  = function(settings_){    
	//jEmotion.settings.input = $(this);
    //$.extend(jEmotion.settings, settings);
	//jEmotion.init(); 
    //return $(this);
    return new jEmotion(settings_).init();
};
