s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.js.JsArray');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.core');
s2js.runtime.client.core.get().classLoader.require('scala.None');
s2js.runtime.client.core.get().classLoader.require('scala.RuntimeException');
s2js.runtime.client.core.get().classLoader.require('scala.Some');
s2js.runtime.client.js.JsArray = function(wrappedArray) {
var self = this;
self.wrappedArray = wrappedArray;
if ((! s2js.runtime.client.core.get().isArray(self.wrappedArray))) {
(function() {
throw new scala.RuntimeException('A non-array can\'t be wrapped with s2js.runtime.client.js.JsArray.');
})();
}
;
};
s2js.runtime.client.js.JsArray.prototype.get = function(index) {
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
})(self.internalGet(index));
};
s2js.runtime.client.js.JsArray.prototype.set = function(index, value) {
var self = this;
self.wrappedArray[index] = value;};
s2js.runtime.client.js.JsArray.prototype.foreach = function(f) {
var self = this;

        for (var i in self.wrappedArray) {
            f(i, self.wrappedArray[i]);
        }
    };
s2js.runtime.client.js.JsArray.prototype.internalGet = function(index) {
var self = this;
return self.wrappedArray[index];};
s2js.runtime.client.js.JsArray.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.js.JsArray', []);
s2js.runtime.client.core.get().mixIn(s2js.runtime.client.js.JsArray, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.fromAny = function(x) {
var self = this;
return (function() {
if (s2js.runtime.client.core.get().isArray(x)) {
return new scala.Some(new s2js.runtime.client.js.JsArray(x));
} else {
return scala.None.get();
}
})();
};
obj.empty = function() {
var self = this;
return new s2js.runtime.client.js.JsArray([]);};
obj.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.js.JsArray', []);
return obj;
}), true);
