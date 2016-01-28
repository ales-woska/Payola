s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.rpc.Reference');
s2js.runtime.client.rpc.Reference = function(targetObjectID) {
var self = this;
self.targetObjectID = targetObjectID;
};
s2js.runtime.client.rpc.Reference.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.rpc.Reference', []);
