s2js.runtime.client.core.get().classLoader.provide('scala.collection.immutable.HashMap');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.Map');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.MapCompanion');
scala.collection.immutable.HashMap = function() {
var self = this;
scala.collection.Map.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.collection.immutable.HashMap, scala.collection.Map);
scala.collection.immutable.HashMap.prototype.newInstance = function() {
var self = this;
return scala.collection.immutable.HashMap.get().empty();
};
scala.collection.immutable.HashMap.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.HashMap', [scala.collection.Map]);
s2js.runtime.client.core.get().mixIn(scala.collection.immutable.HashMap, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj = new scala.collection.MapCompanion();
obj.empty = function() {
var self = this;
return new scala.collection.immutable.HashMap();
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.HashMap', [scala.collection.MapCompanion]);
return obj;
}), true);
