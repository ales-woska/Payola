s2js.runtime.client.core.get().classLoader.provide('scala.collection.immutable.Seq');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.Seq');
scala.collection.immutable.Seq = function() {
var self = this;
scala.collection.Seq.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.collection.immutable.Seq, scala.collection.Seq);
scala.collection.immutable.Seq.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.Seq', [scala.collection.Seq]);
s2js.runtime.client.core.get().mixIn(scala.collection.immutable.Seq, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.canBuildFrom = function() {
var self = this;
return true;
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.Seq', []);
return obj;
}), true);
