/**
 * Created by Administrator on 2016/10/14.
 * 系统登录、登出
 */

secDemoApp.controller('LoginCtrl', ['$rootScope','$scope','$http',
    function($rootScope,$scope,$http){
        $scope.showError = 0;
        //$scope.pwdType = "password";
        //提交输入,进行登录s
        $scope.commitLogin = function(query){
           $scope.showError = 0;
           $scope.loginError = null;
           var url = baseUrl+resUrl.sys.comLogin;
           $http.post(url,query,configJson)
               .success(function (data) {
                   if(data.code == 200){
                       window.location.href = baseUrl+viewPath.base+viewPath.sys.main;
                   }else if(data.code == 500){
                       $scope.loginError = data.loginError;
                       $scope.showError = 1;
                       console.info(data.loginError);
                   }
               }).error(function () {
                   console.log('error....');
               })
        };
        $scope.showForm =function(){
           alert(toBodyString($scope.user));
        };
    }
]);