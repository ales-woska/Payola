s2js.runtime.client.core.get().classLoader.provide('scala.collection.mutable.HashMap');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.Map');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.MapCompanion');
scala.collection.mutable.HashMap = function() {
var self = this;
scala.collection.Map.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.collection.mutable.HashMap, scala.collection.Map);
scala.collection.mutable.HashMap.prototype.newInstance = function() {
var self = this;
return scala.collection.mutable.HashMap.get().empty();
};
scala.collection.mutable.HashMap.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.mutable.HashMap', [scala.collection.Map]);
s2js.runtime.client.core.get().mixIn(scala.collection.mutable.HashMap, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj = new scala.collection.MapCompanion();
obj.empty = function() {
var self = this;
return new scala.collection.mutable.HashMap();
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.mutable.HashMap', [scala.collection.MapCompanion]);
return obj;
}), true);
