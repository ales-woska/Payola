s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.js.JsObject');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.core');
s2js.runtime.client.core.get().classLoader.require('scala.None');
s2js.runtime.client.core.get().classLoader.require('scala.RuntimeException');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
s2js.runtime.client.js.JsObject = function(wrappedObject) {
var self = this;
self.wrappedObject = wrappedObject;
if ((! s2js.runtime.client.core.get().isObject(self.wrappedObject))) {
(function() {
throw new scala.RuntimeException('A non-object can\'t be wrapped with s2js.runtime.client.js.JsObject.');
})();
}
;
};
s2js.runtime.client.js.JsObject.prototype.get = function(propertyName) {
var self = this;
return (function($selector$1) {
if (true) {
var value = $selector$1;
if (s2js.runtime.client.core.get().isDefined(value)) {
return new scala.Some(value);
}
}
if (true) {
return scala.None.get();
}
})(self.internalGet(propertyName));
};
s2js.runtime.client.js.JsObject.prototype.getString = function(propertyName) {
var self = this;
return self.get(propertyName).flatMap(function($x0$1) {
return (function($selector$2) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$2, 'scala.String')) {
var s = $selector$2;
return new scala.Some(s);
}
if (true) {
return scala.None.get();
}
})($x0$1);
});
};
s2js.runtime.client.js.JsObject.prototype.getInt = function(propertyName) {
var self = this;
return self.get(propertyName).flatMap(function($x0$2) {
return (function($selector$3) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$3, 'scala.Int')) {
var s = $selector$3;
return new scala.Some(s);
}
if (true) {
return scala.None.get();
}
})($x0$2);
});
};
s2js.runtime.client.js.JsObject.prototype.getJsArray = function(propertyName) {
var self = this;
return self.get(propertyName).flatMap(function($x$1) {
return s2js.runtime.client.js.JsArray.get().fromAny($x$1);
});
};
s2js.runtime.client.js.JsObject.prototype.set = function(propertyName, value) {
var self = this;
self.wrappedObject[propertyName] = value;};
s2js.runtime.client.js.JsObject.prototype.foreach = function(f) {
var self = this;

        for (var i in self.wrappedObject) {
            f(i, self.wrappedObject[i]);
        }
    };
s2js.runtime.client.js.JsObject.prototype.foreachNonInternal = function(f) {
var self = this;
self.foreach(function(name, value) {
if ((! name.startsWith('__'))) {
f(name, value);
}

});
};
s2js.runtime.client.js.JsObject.prototype.internalGet = function(propertyName) {
var self = this;
return self.wrappedObject[propertyName];};
s2js.runtime.client.js.JsObject.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.js.JsObject', []);
s2js.runtime.client.core.get().mixIn(s2js.runtime.client.js.JsObject, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.fromAny = function(x) {
var self = this;
return (function() {
if (s2js.runtime.client.core.get().isObject(x)) {
return new scala.Some(new s2js.runtime.client.js.JsObject(x));
} else {
return scala.None.get();
}
})();
};
obj.empty = function() {
var self = this;
return new s2js.runtime.client.js.JsObject({});};
obj.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.js.JsObject', []);
return obj;
}), true);
