s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.core.Lazy');
s2js.runtime.client.core.Lazy = function(initializer) {
var self = this;
self.initializer = initializer;
self.value = undefined;
self.isInitialized = false;
};
s2js.runtime.client.core.Lazy.prototype.get = function() {
var self = this;
if ((! self.isInitialized)) {
self.value = self.initializer();
self.isInitialized = true;

}

return self.value;

};
s2js.runtime.client.core.Lazy.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.core.Lazy', []);
