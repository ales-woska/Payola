s2js.runtime.client.core.get().classLoader.provide('scala.collection.immutable.Vector');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.SeqCompanion');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.immutable.Seq');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.List');
scala.collection.immutable.Vector = function() {
var self = this;
scala.collection.immutable.Seq.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.collection.immutable.Vector, scala.collection.immutable.Seq);
scala.collection.immutable.Vector.prototype.newInstance = function() {
var self = this;
return scala.collection.immutable.Vector.get().empty();
};
scala.collection.immutable.Vector.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.Vector', [scala.collection.immutable.Seq]);
s2js.runtime.client.core.get().mixIn(scala.collection.immutable.Vector, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj = new scala.collection.SeqCompanion();
obj.empty = function() {
var self = this;
return new scala.collection.immutable.Vector();
};
obj.$apply = function() {
var self = this;
var xs = scala.collection.immutable.List.get().fromJsArray([].splice.call(arguments, 0, arguments.length - 0));
return self.fromJsArray(xs.getInternalJsArray());};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.Vector', [scala.collection.SeqCompanion]);
return obj;
}), true);
