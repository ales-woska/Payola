s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.core.ClassLoader');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.core');
s2js.runtime.client.core.get().classLoader.require('scala.RuntimeException');
s2js.runtime.client.core.ClassLoader = function() {
var self = this;
self.loadedClasses = new Array();
};
s2js.runtime.client.core.ClassLoader.prototype.isLoaded = function(className) {
var self = this;
return (self.loadedClasses.indexOf(className) != -1);
};
s2js.runtime.client.core.ClassLoader.prototype.declarationRequire = function(className) {
var self = this;
if ((! self.isLoaded(className))) {
(function() {
throw new scala.RuntimeException((('The class ' + className) + ' which hasn\'t been declared yet is required.'));
})();
}

};
s2js.runtime.client.core.ClassLoader.prototype.require = function(className) {
var self = this;

};
s2js.runtime.client.core.ClassLoader.prototype.provide = function(className) {
var self = this;
if ((! self.isLoaded(className))) {
self.loadedClasses.push(className);
s2js.runtime.client.core.get().declareObject(className);

}

};
s2js.runtime.client.core.ClassLoader.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.core.ClassLoader', []);
