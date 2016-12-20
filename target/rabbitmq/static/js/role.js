/**
 * Created by Administrator on 2016/10/19.
 * 角色管理
 */
//角色启用状态显示
secDemoApp.filter('type_enable',function(){
    return function(input){
        switch (input){
            case 1:
                return '启用';
            case 0:
                return '停用';
            case -1:
                return '弃用';
            default :
                return '--';
        }
    }
});

/**
 * 角色添加
 */
secDemoApp.controller('RoleAddCtrl',['$scope','$http',
    function($scope,$http){
        var url = baseUrl + resUrl.role.add;
        $scope.addRole = function(query){
            $http.post(url,query,configJson)
                .success(function (data) {
                if(data.code == 200){
                    alert(data.msg);
                }else if(data.code == 500 ){
                    alert(data.msg);
                }
            })
        }

    }

]);

/**
 *  角色列表
 */
secDemoApp.controller('RoleListCtrl',['$scope','$http',
    function($scope,$http){
        var url = baseUrl + resUrl.role.query;
        var query = '';
        $http.post(url, query, configJson)
            .success(function (data) {
                if (data.code == 200) {
                    $scope.roleList = data.roleList;
                    $scope.dataSize = ($scope.roleList && $scope.roleList.length);
                }else{
                    $scope.dataSize = 0;
                }
            }).error(function () {
                console.log("error...");
            });
    }

]);
/**
 * 角色授权
 */
secDemoApp.controller("BindResCtrl",['$scope','$http',
    function($scope,$http){
        $scope.roleRes = [];//已绑定权限数组
        //从发起的链接中获取角色id和名称
        $scope.role = {
            id:parseInt(getSearch("id")),
            name:getSearch("name")
        };
        //获取绑定权限
        if($scope.role.id){
            var url = baseUrl+resUrl.role.getResList;
            $http.post(url,$scope.role,configJson)
                    .success(function (data) {
                        if(data.code == 200){
                            $scope.pSelect = data.resList;//系统权限
                            $scope.roleRes = data.roleRes;//当前角色已经绑定的权限
                            //$scope.selected = data.selected;//已绑定数组存储
                        }
                    }).error(function(){
                        console.log("error...")
                    });
        }
        //修改绑定权限
        $scope.updateResList= function () {
            var url = baseUrl+resUrl.role.bindRes;
            var query ={
                id : $scope.role.id,
                resList:$scope.roleRes
            };
            $http.post(url,query,configJson)
                .success(function (data) {
                    if(data.code == 200){
                        alert(data.msg);
                        //window.location.href = baseUrl+viewPath.base+viewPath.role.list;
                    }
                }).error(function(){
                    console.log("error...");
                });
        };
        //页面实时勾选已绑定权限
        $scope.isSelected = function(resid){
            return  $scope.roleRes.indexOf(resid) >= 0;
        };
        //更新已绑定权限数组
        var updateSelected = function (action, id) {
            if (action == 'add' && $scope.roleRes.indexOf(id) == -1) {
                $scope.roleRes.push(id);
            }
            else if (action == 'remove' && $scope.roleRes.indexOf(id) != -1) {
                var idx = $scope.roleRes.indexOf(id);
                $scope.roleRes.splice(idx, 1);
            }
        };
        //权限点击  勾选、取消
        $scope.updateSelectedRes = function($event, id){
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add':'remove');
            updateSelected(action,id);
        };
    }
]);

/**
 *  角色修改
 */
secDemoApp.controller('RoleEditCtrl',['$scope','$http',
    function($scope,$http){
        var id = parseInt(getSearch("id"));
        if(id){
            var url = baseUrl+resUrl.role.find+"?id="+id;
            $http.get(url)
                .success(function(data){
                    if(data.code == 200){
                        $scope.role = data.role;
                    }
                }).error(function(response){
                    alert("服务器响应状态值:"+response.status);
                    //加载页面内容
                    document.write(response);
                });
        }
        $scope.editRole = function (query) {
            var url = baseUrl+resUrl.role.edit;
            $http.post(url,query,configJson)
                .success(function(data){
                    if(data.code == 200){
                        alert(data.msg);
                    }
                }).error(function(response){
                    alert("服务器响应状态值:"+response.status);
                    //加载页面内容
                    document.write(response);
                });
        }
    }
]);
