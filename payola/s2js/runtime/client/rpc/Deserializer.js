s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.rpc.Deserializer');
s2js.runtime.client.core.get().classLoader.declarationRequire('s2js.runtime.client.rpc.RpcResultTraverser');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.core');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.js.JsArray');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.js.JsObject');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.rpc.ClassNameRetriever');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.rpc.DeserializationContext');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.rpc.Reference');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.shared.DependencyPackage');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.shared.rpc.RpcException');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.Nil');
s2js.runtime.client.core.get().classLoader.require('scala.collection.mutable.ListBuffer');
s2js.runtime.client.rpc.Deserializer = function() {
var self = this;
s2js.runtime.client.rpc.RpcResultTraverser.apply(self, []);
self.classNameRetriever = new s2js.runtime.client.rpc.ClassNameRetriever();
self.context = new s2js.runtime.client.rpc.DeserializationContext();
};
s2js.runtime.client.core.get().inherit(s2js.runtime.client.rpc.Deserializer, s2js.runtime.client.rpc.RpcResultTraverser);
s2js.runtime.client.rpc.Deserializer.prototype.deserialize = function(value) {
var self = this;
var classesToLoad = self.classNameRetriever.traverse(value).filter(function($x$1) {
return (! s2js.runtime.client.core.get().classLoader.isLoaded($x$1));
});
(function() {
if (classesToLoad.nonEmpty()) {
var loadedClasses = scala.collection.mutable.ListBuffer.get().empty();
new s2js.runtime.client.js.JsArray(s2js.runtime.client.core.get().classLoader.loadedClasses).foreach(function(i, c) {
loadedClasses.$plus$eq(c.toString());


});
return eval(s2js.runtime.client.rpc.Wrapper.get().callSync('s2js.runtime.shared.DependencyProvider.get', [classesToLoad, loadedClasses], ['scala.collection.Seq[java.lang.String]', 'scala.collection.mutable.ListBuffer[java.lang.String]']).javaScript);

}
})()
self.context = new s2js.runtime.client.rpc.DeserializationContext();
var result = self.traverse(value);
self.context.resolveReferences();
return result;

};
s2js.runtime.client.rpc.Deserializer.prototype.nonFutureInstanceVisitor = function(nonInstance, items) {
var self = this;
var referencedObjectId = s2js.runtime.client.js.JsObject.get().fromAny(nonInstance).flatMap(function($x$2) {
return $x$2.getInt('__ref__');
});
return (function() {
if (referencedObjectId.isDefined()) {
return new s2js.runtime.client.rpc.Reference(referencedObjectId.get());
} else {
return (function() {
if (s2js.runtime.client.core.get().isArray(nonInstance)) {
return items;
} else {
return nonInstance;
}
})();
}
})();

};
s2js.runtime.client.rpc.Deserializer.prototype.futureInstanceVisitor = function(jsObject, properties, className) {
var self = this;
var instance = self.createInstance(className);
var instanceJsObject = new s2js.runtime.client.js.JsObject(instance);
(function($selector$1) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$1, 'scala.collection.Seq')) {
self.deserializeSeq(instanceJsObject, properties);
return;
}
if (true) {
self.deserializeObject(instanceJsObject, properties, jsObject);
return;
}
})(instance);
self.context.registerInstance(jsObject, instance);
return instance;

};
s2js.runtime.client.rpc.Deserializer.prototype.deserializeSeq = function(instanceJsObject, properties) {
var self = this;
var items = (function($selector$2) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$2, 'scala.Some') && (s2js.runtime.client.core.get().isInstanceOf($selector$2.productElement(0), 'scala.collection.Seq'))) {
var seq = $selector$2.productElement(0);
return seq;
}
if (true) {
return scala.collection.immutable.Nil.get();
}
})(properties.get('__value__'));
instanceJsObject.set('internalJsArray', items.toBuffer());
var index = 0;
items.foreach(function(item) {
self.context.addReference(item, instanceJsObject, (('internalJsArray[' + index) + ']'));
index = (index + 1);

});

};
s2js.runtime.client.rpc.Deserializer.prototype.deserializeObject = function(instanceJsObject, properties, jsObject) {
var self = this;
jsObject.foreachNonInternal(function(propertyName, propertyValue) {
var traversedPropertyValue = properties.$apply(propertyName);
instanceJsObject.set(propertyName, traversedPropertyValue);
self.context.addReference(traversedPropertyValue, instanceJsObject, propertyName);

});
};
s2js.runtime.client.rpc.Deserializer.prototype.createInstance = function(className) {
var self = this;
if ((! s2js.runtime.client.core.get().classLoader.isLoaded(className))) {
(function() {
throw new s2js.runtime.shared.rpc.RpcException((('Can\'t deserialize an instance of class ' + className) + '. The class isn\'t loaded.'), undefined, undefined);
})();
}

return eval((('new ' + className) + '()'));

};
s2js.runtime.client.rpc.Deserializer.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.rpc.Deserializer', [s2js.runtime.client.rpc.RpcResultTraverser]);
