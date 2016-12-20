/**
 * Created by Administrator on 2016/10/24.
 * 用户
 */


/**
 * 用户列表
 */
secDemoApp.controller('UserListCtrl',['$scope','$http',
    function($scope,$http){
        var url = baseUrl + resUrl.users.query;
        $scope.dataSize = 0;
        var query = '';
        $http.post(url, query, configJson)
            .success(function (data) {
                if (data.code == 200) {
                    $scope.uList = data.list;
                    $scope.dataSize = ($scope.uList && $scope.uList.length);
                }
            });
    }
]);

/**
 *  用户角色绑定
 */
secDemoApp.controller('BindRoleCtrl',['$scope','$http',
    function($scope,$http){
        //当前用户信息
        $scope.user = {
            userId:parseInt(getSearch("id")),
            userName:getSearch("name")
        };
        var roleGetUrl = baseUrl+resUrl.users.selectRole;
        //角色获取:所有角色和当前用户已绑定角色
        $http.post(roleGetUrl, $scope.user, configJson)
            .success(function (data) {
                if (data.code == 200) {
                    $scope.currentRoles = data.currentRoles;
                    $scope.rSelect = data.rSelect;
                }
            });
        //已经绑定的角色做勾选显示
        $scope.isSelected = function(roleId){
            return $scope.currentRoles.indexOf(roleId)!=-1;
        };
        //点击角色勾选
        $scope.updateSelectedRole = function($event,roleId){
            var checkbox = $event.target;
            var action = (checkbox.checked ? 'add':'remove');
            updateSelected(action,roleId);
        };
        //更新已绑定权限数组
        var updateSelected = function (action, id) {
            if (action == 'add' && $scope.currentRoles.indexOf(id) == -1) {
                $scope.currentRoles.push(id);
            }
            else if (action == 'remove' && $scope.currentRoles.indexOf(id) != -1) {
                var idx = $scope.currentRoles.indexOf(id);
                $scope.currentRoles.splice(idx, 1);
            }
        };

        // 提交修改后的绑定角色
        $scope.updateRoleList = function(){
            var url = baseUrl+resUrl.users.bindRole;
            $scope.user.roleList = $scope.currentRoles;
            $http.post(url,$scope.user,configJson)
                .success(function(data){
                   if(data.code == 200){
                       alert(data.msg);
                   }
                });
        }
    }
]);
