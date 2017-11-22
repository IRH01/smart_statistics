/**
 * The application module.
 *
 * @author Iritchie.ren
 * @version 1.0
 */
var app = angular.module('app', [], function () {
}).run(function ($rootScope, $http) {

});

app.factory('httpInterceptor', ['$q', function ($q) {

    var UNEXPECTED_ERROR_CODES = ['01', '02'];

    return {

        request: function (config) {
            if (config.method != 'GET') {
                config.headers[$("meta[name='_csrf_header']").attr("content")] = $("meta[name='_csrf']").attr("content");
            }
            return config;
        },

        response: function (response) {
            if (response.status != 200) {
                console.error(response);
                return $q.reject(response);
            }
            if (response.data.code != '0' && UNEXPECTED_ERROR_CODES.indexOf(response.data.code) != -1) {
                console.error(response.data.message);
                return $q.reject(response);
            }
            return response;
        }
    };
}]);

app.config(['$httpProvider', function ($httpProvider) {
    // Registers the HTTP interceptor.
    $httpProvider.interceptors.push('httpInterceptor');
}]);

var HelperService = app.factory('HelperService', ['$http', function ($http) {

    var PAGE_SIZE = 10;

    var MAX_IMG_SIZE = 204800;

    return {
        PAGE_SIZE: PAGE_SIZE,

        uploadImage: function (model, input, callback) {
            var self = this;
            if (input.files.length == 0) {
                return callback();
            }
            var file = input.files[0];
            if (file.size > MAX_IMG_SIZE) {
                self.showMessage("图片文件不能大于200KB");
                return callback();
            }
            var formData = new FormData();
            formData.append('model', model);
            formData.append('file', file);
            var headers = {};
            headers[$("meta[name='_csrf_header']").attr("content")] = $("meta[name='_csrf']").attr("content");
            $.ajax({
                url: '/api/file-upload',
                type: 'POST',
                processData: false,
                contentType: false,
                headers: headers,
                data: formData,
                success: function (result) {
                    if (result.code != '0') {
                        self.showMessage("图片上传失败，请重试");
                        return callback();
                    }
                    callback(result.data);
                },
                error: function () {
                    self.showMessage("图片上传失败，请重试");
                    callback();
                }
            });
        },

        showMessage: function (message) {
            alert(message);
        },

        getRequestParam: function (name) {
            if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search)) {
                return decodeURIComponent(name[1]);
            }
            return null;
        },

        replaceListItem: function (list, item) {
            for (var i in list) {
                if (list[i].id == item.id) {
                    list.splice(i, 1, item);
                    return;
                }
            }
        },

        createSearchUrl: function (url, filter) {
            if (filter && Object.getOwnPropertyNames(filter).length > 0) {
                url += '?';
                for (var key in filter) {
                    if (filter[key]) {
                        url += key + '=';
                        if (filter[key] instanceof Array) {
                            for (var i in filter[key]) {
                                url += encodeURIComponent(filter[key][i]);
                                if (i < filter[key].length - 1) {
                                    url += ',';
                                }
                            }
                        } else {
                            url += encodeURIComponent(filter[key]);
                        }
                        url += '&';
                    }
                }
                return url.substring(0, url.length - 1)
            }
            return url;
        },

        search: function (url, filter, pageHandler, callback) {
            url = this.createSearchUrl(url, filter);

            return $http.get(url).success(function (result) {
                if (result.code != 0) {
                    return;
                }
                var searchResult = result.data;
                if (pageHandler) {
                    this.renderPagers(pageHandler, searchResult);
                }
                if (callback) {
                    callback(searchResult);
                }
            }.bind(this));
        },

        formatDate: function (dateValue, excludeTime) {
            if (!dateValue) {
                return '';
            }
            var date = new Date(dateValue);
            var result = date.getFullYear() + '-' + this.getDoubleNumberString(date.getMonth() + 1) + '-'
                + this.getDoubleNumberString(date.getDate());
            if (!excludeTime) {
                result += ' ' + this.getDoubleNumberString(date.getHours())
                    + ':' + this.getDoubleNumberString(date.getMinutes());
            }
            return result;
        },

        getDoubleNumberString: function (number) {
            return number >= 10 ? number.toString() : '0' + number;
        },

        copySimpleObject: function (obj) {
            if (!obj) {
                return null;
            }
            var result = {};
            var keys = Object.getOwnPropertyNames(obj);
            for (var i in keys) {
                var key = keys[i];
                result[key] = obj[key];
            }
            return result;
        },

        tipErrorMessage: function ($scope, message) {
            $scope.tipMessage = message;
            $('.fail-tips').show().animate({'opacity': 1}, 300).delay(3000).animate({'opacity': 0}, 300, function () {
                $(this).hide()
            });
        },

        tipSuccessMessage: function ($scope, message) {
            $scope.tipMessage = message;
            $('.success-tips').show().animate({'opacity': 1}, 300).delay(3000).animate({'opacity': 0}, 300, function () {
                $(this).hide()
            });
        }
    }
}]);