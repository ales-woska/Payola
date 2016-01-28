s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.rpc.DeserializationContext');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.rpc.ReferenceToResolve');
s2js.runtime.client.core.get().classLoader.require('scala.collection.mutable.ArrayBuffer');
s2js.runtime.client.core.get().classLoader.require('scala.collection.mutable.HashMap');
s2js.runtime.client.rpc.DeserializationContext = function() {
var self = this;
self.objectRegistry = new scala.collection.mutable.HashMap();
self.references = new scala.collection.mutable.ArrayBuffer();
};
s2js.runtime.client.rpc.DeserializationContext.prototype.resolveReferences = function() {
var self = this;
self.references.foreach(function($x$1) {
$x$1.resolve(self);
});
};
s2js.runtime.client.rpc.DeserializationContext.prototype.addReference = function(reference, instanceJsObject, propertyName) {
var self = this;
(function($selector$1) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$1, 's2js.runtime.client.rpc.Reference')) {
var r = $selector$1;
self.references.$plus$eq(new s2js.runtime.client.rpc.ReferenceToResolve(r, instanceJsObject.wrappedObject, propertyName));


return;
}
if (true) {

return;
}
})(reference);
};
s2js.runtime.client.rpc.DeserializationContext.prototype.registerInstance = function(jsObject, instance) {
var self = this;
jsObject.getInt('__objectID__').foreach(function(id) {
return self.objectRegistry.put(id, instance);
});
};
s2js.runtime.client.rpc.DeserializationContext.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.rpc.DeserializationContext', []);
