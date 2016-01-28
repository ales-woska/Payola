s2js.runtime.client.core.get().classLoader.provide('scala.Predef');
s2js.runtime.client.core.get().classLoader.require('scala.IllegalArgumentException');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.StringOps');
s2js.runtime.client.core.get().mixIn(scala.Predef, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.augmentString = function(x) {
var self = this;
return new scala.collection.immutable.StringOps(x);
};
obj.unaugmentString = function(x) {
var self = this;
return x.repr();
};
obj.require = function(requirement, message) {
var self = this;
if ((! requirement)) {
(function() {
throw new scala.IllegalArgumentException(('requirement failed: ' + message), undefined);
})();
}

};
obj.refArrayOps = function(x) {
var self = this;
return x;
};
obj.fallbackStringCanBuildFrom = function() {
var self = this;
return true;
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Predef', []);
return obj;
}), true);
