s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.rpc.Wrapper');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.core');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.rpc.Deserializer');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.shared.rpc.RpcException');
s2js.runtime.client.core.get().classLoader.require('scala.collection.Seq');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.List');
s2js.runtime.client.core.get().classLoader.require('scala.collection.mutable.ArrayBuffer');
s2js.runtime.client.core.get().mixIn(s2js.runtime.client.rpc.Wrapper, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.deserializer = new s2js.runtime.client.rpc.Deserializer();
obj.parameterSeparator = '&';
obj.requestReadyStateDone = 4;
obj.callSync = function(procedureName, parameters, parameterTypes) {
var self = this;
var request = s2js.runtime.client.rpc.Wrapper.get().createXmlHttpRequest('/RPC', false);
request.send(s2js.runtime.client.rpc.Wrapper.get().createRequestBody(procedureName, parameters, parameterTypes));
return s2js.runtime.client.rpc.Wrapper.get().processRequestResult(request, function(x$1) {

}, function(throwable) {
return (function() {
throw throwable;
})();
});

};
obj.callAsync = function(procedureName, parameters, parameterTypes, successCallback, exceptionCallback) {
var self = this;
var request = s2js.runtime.client.rpc.Wrapper.get().createXmlHttpRequest('/RPC/async', true);
request.onreadystatechange = function(x$2) {
if (((request.readyState == s2js.runtime.client.rpc.Wrapper.get().requestReadyStateDone) && (request.status != 0))) {
s2js.runtime.client.rpc.Wrapper.get().processRequestResult(request, successCallback, exceptionCallback);


}

};
request.send(s2js.runtime.client.rpc.Wrapper.get().createRequestBody(procedureName, parameters, parameterTypes));

};
obj.createXmlHttpRequest = function(controllerUrl, isAsync) {
var self = this;
var request = (function() {
if (s2js.runtime.client.core.get().isObjectDefined('XMLHttpRequest')) {
return new XMLHttpRequest();
} else {
return new ActiveXObject('Msxml2.XMLHTTP');
}
})();
request.open('POST', controllerUrl, isAsync, undefined, undefined);
request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
return request;

};
obj.processRequestResult = function(request, onSuccess, onException) {
var self = this;
var result = (function() {
if (scala.collection.immutable.List.get().$apply(200, 500).contains(request.status)) {
return (function() {
var $tryReturnValue$1 = undefined;
try {
$tryReturnValue$1 = (function() {
return s2js.runtime.client.rpc.Wrapper.get().deserializer.deserialize(eval((('(' + request.responseText) + ')')));
})();
} catch ($ex$2) {
$tryReturnValue$1 = (function() {
if (true) {
var error = $ex$2;
var description = (function($selector$3) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$3, 's2js.runtime.shared.rpc.RpcException')) {
var e = $selector$3;
return e.message;
}
if (true) {
return error.toString();
}
})(error);
return new s2js.runtime.shared.rpc.RpcException('Exception during deserialization of the remote method result.', description, undefined);

}
throw $ex$2;
})();
}
return $tryReturnValue$1;
})()
} else {
return new s2js.runtime.shared.rpc.RpcException((('The RPC call exited with status code ' + request.status) + '.'), undefined, undefined);
}
})();
return (function($selector$4) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$4, 'scala.Throwable')) {
var throwable = $selector$4;
onException(throwable);
return throwable;

}
if (true) {
var value = $selector$4;
onSuccess(value);
return value;

}
})(result);

};
obj.createRequestBody = function(procedureName, parameters, parameterTypeNames) {
var self = this;
var requestBody = new scala.collection.mutable.ArrayBuffer();
requestBody.$plus$eq(('method=' + procedureName));
requestBody.$plus$eq((s2js.runtime.client.rpc.Wrapper.get().parameterSeparator + 'paramTypes='));
requestBody.$plus$eq(encodeURIComponent(parameterTypeNames.map(function($value) {
return s2js.runtime.client.rpc.Wrapper.get().jsonEscapeAndQuote($value);
}, scala.collection.mutable.ArrayBuffer.get().canBuildFrom()).mkString('[', ',', ']')));
var index = -1;
parameters.foreach(function(parameterValue) {
index = (index + 1);
requestBody.$plus$eq(((s2js.runtime.client.rpc.Wrapper.get().parameterSeparator + index) + '='));
return requestBody.$plus$eq(encodeURIComponent(s2js.runtime.client.rpc.Wrapper.get().processParameter(parameterTypeNames.$apply(index), parameterValue)));

});
return requestBody.mkString();

};
obj.processParameter = function(typeName, value) {
var self = this;
return (function($selector$5) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$5, 'scala.String')) {
var s = $selector$5;
return s;
}
if (s2js.runtime.client.core.get().isInstanceOf($selector$5, 'scala.collection.immutable.StringOps')) {
var s = $selector$5;
return s.toString();
}
if (s2js.runtime.client.core.get().isInstanceOf($selector$5, 'scala.collection.Seq')) {
var items = $selector$5;
var escapedItems = (function() {
if ((typeName.endsWith('[scala.String]') || typeName.endsWith('[java.lang.String]'))) {
return items.map(function(item) {
return s2js.runtime.client.rpc.Wrapper.get().jsonEscapeAndQuote(item.toString());
}, scala.collection.Seq.get().canBuildFrom());
} else {
return items.map(function($x$3) {
return $x$3.toString();
}, scala.collection.Seq.get().canBuildFrom());
}
})();
return escapedItems.mkString('[', ',', ']');

}
if (true) {
var x = $selector$5;
return x.toString();
}
})(value);
};
obj.jsonEscapeAndQuote = function(value) {
var self = this;
return (('\"' + s2js.runtime.client.rpc.Wrapper.get().jsonEscape(value)) + '\"');
};
obj.jsonEscape = function(value) {
var self = this;

        return value.replace('\\', '\\\\').replace('"', '\\"').replace("'", "\\'");
    };
obj.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.rpc.Wrapper', []);
return obj;
}), true);
