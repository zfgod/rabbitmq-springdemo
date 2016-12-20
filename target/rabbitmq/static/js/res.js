/**
 * Created by Administrator on 2016/10/17.
 * 系统权限资源
 */
/** 抽取功能,对象
 * */

var resTypeSelect = [
    {name: '菜单',id:1},
    {name: '页面',id:2},
    {name: '操作',id:3}
];

//资源类型匹配
secDemoApp.filter('type_resType',function(){
    return function(input){
        switch (input){
            //数据为string,加单引号;为number 不加单引号
            case '1':
                return '菜单';
            case '2':
                return '页面';
            case '3':
                return '操作';
            default:
                return '--';
        }
    }
});

//资源上级下拉选择
secDemoApp.factory('resourceService', ['$http',
    function($http) {
        return {
            //上级资源下拉获取
            resParentList:function (){
                var select = baseUrl + resUrl.resources.resSelect;
                return $http.get(select);
            }
        }
    }
]);

/**列表页面
 * */
secDemoApp.controller('ResListCtrl',['$scope','$http',
    function($scope,$http){
        var url = baseUrl+resUrl.resources.query;
        //页面加载完成，请求资源数据
        $http.get(url)
            .success(function(data){
                if(data.code = 200){
                    $scope.resList = data.resList;
                    $scope.dataSize = ($scope.resList && $scope.resList.length);
                }else{
                    $scope.dataSize = 0;
                }
            })
            .error(function(){
                console.log("error...");
            });
    }
]);



/** 编辑页面
 * */

secDemoApp.controller('ResEditCtrl', ['$scope','$http','resourceService',
    function($scope,$http,resourceService){
        $scope.resTypeSelect = resTypeSelect;
        var id = parseInt(getSearch("id"));
        if(id && judgeInt(id)){
            var url = baseUrl+resUrl.resources.find+"?id="+id;
            $http.get(url)
                .success(function(data){
                    if(data.code == 200){
                        $scope.res = data.res;
                    }
                })
                .error(function(){
                   console.log("error");
                });
            resourceService.resParentList()
                .success(function(data){
                    if(data.code == 200){
                        $scope.pSelect = data.resList;
                    }
                }).error(function(){
                    console.log("error");
                });

        }
        $scope.editRes=function(query){
            var url = baseUrl+resUrl.resources.edit;
            $http.post(url,query,configJson)
                .success(function(data){
                    if(data.code == 200){
                        alert(data.msg);
                    }
                })
        }
    }
]);

/**
 * 添加页面
 * */


secDemoApp.controller('ResAddCtrl',['$scope','$http','resourceService',
    function($scope,$http,resourceService){
        $scope.resTypeSelect = resTypeSelect;
        var select =baseUrl+resUrl.resources.resSelect;
        $http.get(select)
            .success(function(data){
                if(data.code == 200){
                    $scope.pSelect = data.resList;
                }
            }).error(function(){
                console.log("error");
            });
        resourceService.resParentList()
            .success(function(data){
               if(data.code == 200){
                $scope.pSelect = data.resList;
               }
            }).error(function(){
              console.log("error");
            });

        $scope.addRes=function(query){
            var url = baseUrl+resUrl.resources.add;
            $http.post(url,query,configJson)
                .success(function(data){
                    if(data.code==200){
                        alert(data.msg);
                        window.location.href =
                            baseUrl+viewPath.base+viewPath.resources.list;
                    }else if(data.code == 500){
                        alert(data.msg);
                    }
                }).error(function(){
                    console.log("error...");
                });
        }
    }
]);
