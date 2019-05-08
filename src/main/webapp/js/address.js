layui.define(["form","jquery"],function(exports){
    var form = layui.form,
    $ = layui.jquery,
    Address = function(){};

    Address.prototype.provinces = function() {
        //获取路径
        var pathName=window.document.location.pathname;
       //截取，得到项目名称
        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        //加载省数据
        var proHtml = '',that = this;
        $.get(projectName+"/json/address.json", function (data) {
            for (var i = 0; i < data.length; i++) {
                proHtml += '<option value="' + data[i].name + '">' + data[i].name + '</option>';//改了
            }
            //初始化省数据
            $("select[name=province]").append(proHtml);
            form.render();
            form.on('select(province)', function (proData) {
                $("select[name=area]").html('<option value="">请选择县/区</option>');
                var value = proData.value;
                 console.log(value);
                if (value !=null|| value!="") {
                    that.citys(data[$(this).index() - 1].childs);

                } else {
                    $("select[name=city]").attr("disabled", "disabled");
                }
            });
        })
    }

    //加载市数据
    Address.prototype.citys = function(citys) {
        var cityHtml = '<option value="">请选择市</option>',that = this;
        for (var i = 0; i < citys.length; i++) {
            cityHtml += '<option value="' + citys[i].name + '">' + citys[i].name + '</option>';
        }
        $("select[name=city]").html(cityHtml).removeAttr("disabled");
        form.render();
        form.on('select(city)', function (cityData) {
            var value = cityData.value;
            if (value !=null|| value!="") {
                that.areas(citys[$(this).index() - 1].childs);
            } else {
                $("select[name=area]").attr("disabled", "disabled");
            }
        });
    }

    //加载县/区数据
    Address.prototype.areas = function(areas) {
        var areaHtml = '<option value="">请选择县/区</option>';
        for (var i = 0; i < areas.length; i++) {
            areaHtml += '<option value="' + areas[i].name + '">' + areas[i].name + '</option>';
        }
        $("select[name=area]").html(areaHtml).removeAttr("disabled");
        form.render();
    }

    var address = new Address();
    exports("address",function(){
        address.provinces();
    });
})