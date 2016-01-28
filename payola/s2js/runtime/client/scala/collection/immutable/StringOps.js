s2js.runtime.client.core.get().classLoader.provide('scala.collection.immutable.StringOps');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.Seq');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.SeqCompanion');
s2js.runtime.client.core.get().classLoader.require('scala.Option');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.List');
scala.collection.immutable.StringOps = function(str) {
var self = this;
self.str = str;
scala.collection.Seq.apply(self, []);
self.x = scala.Option.get().$apply(self.str).getOrElse('');
self.initializeInternalJsArray(self.x);
};
s2js.runtime.client.core.get().inherit(scala.collection.immutable.StringOps, scala.collection.Seq);
scala.collection.immutable.StringOps.prototype.newInstance = function() {
var self = this;
return scala.collection.immutable.StringOps.get().empty();
};
scala.collection.immutable.StringOps.prototype.initializeInternalJsArray = function(value) {
var self = this;
self.setInternalJsArray(value.split(''))};
scala.collection.immutable.StringOps.prototype.repr = function() {
var self = this;
return self.getInternalJsArray().join('');};
scala.collection.immutable.StringOps.prototype.toBoolean = function() {
var self = this;
return self.x == 'true';};
scala.collection.immutable.StringOps.prototype.toByte = function() {
var self = this;
return parseInt(self.x);};
scala.collection.immutable.StringOps.prototype.toShort = function() {
var self = this;
return parseInt(self.x);};
scala.collection.immutable.StringOps.prototype.toInt = function() {
var self = this;
return parseInt(self.x);};
scala.collection.immutable.StringOps.prototype.toLong = function() {
var self = this;
return parseInt(self.x);};
scala.collection.immutable.StringOps.prototype.toFloat = function() {
var self = this;
return parseFloat(self.x);};
scala.collection.immutable.StringOps.prototype.toDouble = function() {
var self = this;
return parseFloat(self.x);};
scala.collection.immutable.StringOps.prototype.split = function(pattern) {
var self = this;
return self.repr().split(pattern);};
scala.collection.immutable.StringOps.prototype.replaceAllLiterally = function(pattern, replacement) {
var self = this;
return self.x.replace(pattern, replacement);};
scala.collection.immutable.StringOps.prototype.format = function(args) {
var self = this;

         var copiedArgs = [];
          for(var i=0; i<arguments.length; i++){
              copiedArgs.push(arguments[i]);
          }
         try {
             return vsprintf(self.str, copiedArgs);
         }catch(err){
            console.error("Error evaluating sprinf on '%s'", self.str);
            return self.str;
         }
        };
scala.collection.immutable.StringOps.prototype.toString = function() {
var self = this;
return self.repr();
};
scala.collection.immutable.StringOps.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.StringOps', [scala.collection.Seq]);
s2js.runtime.client.core.get().mixIn(scala.collection.immutable.StringOps, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj = new scala.collection.SeqCompanion();
obj.empty = function() {
var self = this;
return new scala.collection.immutable.StringOps('');
};
obj.$apply = function() {
var self = this;
var xs = scala.collection.immutable.List.get().fromJsArray([].splice.call(arguments, 0, arguments.length - 0));
return self.fromJsArray(xs.getInternalJsArray());};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.StringOps', [scala.collection.SeqCompanion]);
return obj;
}), true);
