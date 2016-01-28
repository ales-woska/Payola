s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.core.Class');
s2js.runtime.client.core.Class = function(fullName, parentClasses) {
var self = this;
self.fullName = fullName;
self.parentClasses = parentClasses;
};
s2js.runtime.client.core.Class.prototype.getName = function() {
var self = this;
return self.fullName;
};
s2js.runtime.client.core.Class.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.core.Class', []);
