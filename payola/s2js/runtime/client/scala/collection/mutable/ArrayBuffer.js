s2js.runtime.client.core.get().classLoader.provide('scala.collection.mutable.ArrayBuffer');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.Seq');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.SeqCompanion');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.List');
scala.collection.mutable.ArrayBuffer = function() {
var self = this;
scala.collection.Seq.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.collection.mutable.ArrayBuffer, scala.collection.Seq);
scala.collection.mutable.ArrayBuffer.prototype.newInstance = function() {
var self = this;
return scala.collection.mutable.ArrayBuffer.get().empty();
};
scala.collection.mutable.ArrayBuffer.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.mutable.ArrayBuffer', [scala.collection.Seq]);
s2js.runtime.client.core.get().mixIn(scala.collection.mutable.ArrayBuffer, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj = new scala.collection.SeqCompanion();
obj.empty = function() {
var self = this;
return new scala.collection.mutable.ArrayBuffer();
};
obj.$apply = function() {
var self = this;
var xs = scala.collection.immutable.List.get().fromJsArray([].splice.call(arguments, 0, arguments.length - 0));
return self.fromJsArray(xs.getInternalJsArray());};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.mutable.ArrayBuffer', [scala.collection.SeqCompanion]);
return obj;
}), true);
