/**
* Copyright (c) <2013-> <xuechong87@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

* @author xuechong  <xuechong87@gmail.com>
* @date 2012.12.9
*/

$.fn.simpleWeather = function(options){
        defaultops = {
                cityId : '',
                dataUrl :''
        };

        var weather = new Object();
        weather.opts = $.extend(defaultops,options);
        weather._this = $(this);
        weather.init = function(){
                $.getJSON(weather.opts.dataUrl,{cityId:weather.opts.cityId},function(data){
                        var infos = data.weatherinfo;

                        var cityName = "<div class='weather_cityName'>" + infos.city + "</div>";
                        var dateNow = "<div class='weather_dateNow weather_content'>" 
                                +infos.date_y + infos.week + "</div>";
                        var weatherDes = "<div class='weather_desc weather_content'>" 
                                + infos.weather1+"&nbsp;"+infos.wind1 + "</div>";
                        var img1 = "<div class='weather_img1' ><img src='http://m.weather.com.cn/img/b"
                                 + infos.img1 + ".gif'  /></div>";
                        //var img2 = "<img src='" + IMG_BASE_URL + infos.img2 + ".gif' class='weather_img2' />";
                        var temp = "<div class='weather_temp weather_content'>" + infos.temp1 + "</div>";
                        var wind = "<div class='weather_xiche weather_content'>" + infos.index_xc + "洗车</div>";

                        weather._this.addClass('weather_parent');

                        weather._this.html(cityName + dateNow  + img1  +  temp + weatherDes  + wind);

                });
        };

        weather.reload = weather.init;
        weather.init();
        return weather;
};