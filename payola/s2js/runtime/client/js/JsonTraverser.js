s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.js.JsonTraverser');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.core');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.js.JsArray');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.js.JsObject');
s2js.runtime.client.core.get().classLoader.require('scala.collection.mutable.ArrayBuffer');
s2js.runtime.client.core.get().classLoader.require('scala.collection.mutable.HashMap');
s2js.runtime.client.js.JsonTraverser = function() {
var self = this;
};
s2js.runtime.client.js.JsonTraverser.prototype.traverse = function(value) {
var self = this;
return (function() {
if (s2js.runtime.client.core.get().isArray(value)) {
var jsArray = new s2js.runtime.client.js.JsArray(value);
var traversedItems = new scala.collection.mutable.ArrayBuffer();
jsArray.foreach(function(index, item) {
traversedItems.$plus$eq(self.traverse(item));


});
return self.arrayVisitor(jsArray, traversedItems);

} else {
return (function() {
if (s2js.runtime.client.core.get().isObject(value)) {
var jsObject = new s2js.runtime.client.js.JsObject(value);
var traversedProperties = new scala.collection.mutable.HashMap();
if (self.objectIsTraversable(jsObject)) {
jsObject.foreach(function(name, value) {
traversedProperties.put(name, self.traverse(value));


});
}

return self.objectVisitor(jsObject, traversedProperties);

} else {
return self.valueVisitor(value);
}
})();
}
})();
};
s2js.runtime.client.js.JsonTraverser.prototype.objectIsTraversable = function(jsObject) {
var self = this;
return true;
};
s2js.runtime.client.js.JsonTraverser.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.js.JsonTraverser', []);
