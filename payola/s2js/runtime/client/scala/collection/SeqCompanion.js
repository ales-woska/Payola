s2js.runtime.client.core.get().classLoader.provide('scala.collection.SeqCompanion');
scala.collection.SeqCompanion = function() {
var self = this;
};
scala.collection.SeqCompanion.prototype.fromJsArray = function(jsArray) {
var self = this;

        var a = self.empty();
        a.setInternalJsArray(jsArray);
        return a;
    };
scala.collection.SeqCompanion.prototype.canBuildFrom = function() {
var self = this;
return true;
};
scala.collection.SeqCompanion.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.SeqCompanion', []);
