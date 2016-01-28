s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.rpc.ClassNameRetriever');
s2js.runtime.client.core.get().classLoader.declarationRequire('s2js.runtime.client.rpc.RpcResultTraverser');
s2js.runtime.client.core.get().classLoader.require('scala.collection.Seq');
s2js.runtime.client.core.get().classLoader.require('scala.collection.mutable.ArrayBuffer');
s2js.runtime.client.rpc.ClassNameRetriever = function() {
var self = this;
s2js.runtime.client.rpc.RpcResultTraverser.apply(self, []);
};
s2js.runtime.client.core.get().inherit(s2js.runtime.client.rpc.ClassNameRetriever, s2js.runtime.client.rpc.RpcResultTraverser);
s2js.runtime.client.rpc.ClassNameRetriever.prototype.nonFutureInstanceVisitor = function(nonInstance, items) {
var self = this;
return items.flatMap(function(item) {
return item;
}, scala.collection.Seq.get().canBuildFrom());
};
s2js.runtime.client.rpc.ClassNameRetriever.prototype.futureInstanceVisitor = function(jsObject, properties, className) {
var self = this;
var result = scala.collection.mutable.ArrayBuffer.get().$apply(className);
properties.foreach(function(pair) {
return result.$plus$plus$eq(pair._2);
});
return result;

};
s2js.runtime.client.rpc.ClassNameRetriever.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.rpc.ClassNameRetriever', [s2js.runtime.client.rpc.RpcResultTraverser]);
