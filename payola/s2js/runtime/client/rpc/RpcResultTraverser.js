s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.rpc.RpcResultTraverser');
s2js.runtime.client.core.get().classLoader.declarationRequire('s2js.runtime.client.js.JsonTraverser');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.Nil');
s2js.runtime.client.rpc.RpcResultTraverser = function() {
var self = this;
s2js.runtime.client.js.JsonTraverser.apply(self, []);
};
s2js.runtime.client.core.get().inherit(s2js.runtime.client.rpc.RpcResultTraverser, s2js.runtime.client.js.JsonTraverser);
s2js.runtime.client.rpc.RpcResultTraverser.prototype.objectVisitor = function(jsObject, properties) {
var self = this;
return (function($selector$1) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$1, 'scala.Some') && (s2js.runtime.client.core.get().isInstanceOf($selector$1.productElement(0), 'scala.String'))) {
var className = $selector$1.productElement(0);
return self.futureInstanceVisitor(jsObject, properties, className);
}
if (true) {
return self.nonFutureInstanceVisitor(jsObject.wrappedObject, scala.collection.immutable.Nil.get());
}
})(self.getObjectClass(jsObject));
};
s2js.runtime.client.rpc.RpcResultTraverser.prototype.arrayVisitor = function(jsArray, items) {
var self = this;
return self.nonFutureInstanceVisitor(jsArray.wrappedArray, items);
};
s2js.runtime.client.rpc.RpcResultTraverser.prototype.valueVisitor = function(value) {
var self = this;
return self.nonFutureInstanceVisitor(value, scala.collection.immutable.Nil.get());
};
s2js.runtime.client.rpc.RpcResultTraverser.prototype.objectIsTraversable = function(jsObject) {
var self = this;
return (function($selector$2) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$2, 'scala.Some') && (s2js.runtime.client.core.get().isInstanceOf($selector$2.productElement(0), 'scala.String'))) {
return true;
}
if (true) {
return false;
}
})(self.getObjectClass(jsObject));
};
s2js.runtime.client.rpc.RpcResultTraverser.prototype.getObjectClass = function(jsObject) {
var self = this;
return jsObject.get('__class__').orElse(jsObject.get('__arrayClass__'));
};
s2js.runtime.client.rpc.RpcResultTraverser.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.rpc.RpcResultTraverser', [s2js.runtime.client.js.JsonTraverser]);
