s2js.runtime.client.core.get().classLoader.provide('scala.String');
scala.String = function() {
var self = this;
};
scala.String.prototype.contains = function(sequence) {
var self = this;
return (self.indexOf(sequence) !== -1)};
scala.String.prototype.$length = function() {
var self = this;
return self.length;};
scala.String.prototype.$indexOf = function(c) {
var self = this;
return self.indexOf(c);};
scala.String.prototype.$lastIndexOf = function(c) {
var self = this;
return self.lastIndexOf(c);};
scala.String.prototype.startsWith = function(that) {
var self = this;
return self.asStringOps().startsWith(that.asStringOps());
};
scala.String.prototype.endsWith = function(that) {
var self = this;
return self.asStringOps().endsWith(that.asStringOps());
};
scala.String.prototype.asStringOps = function() {
var self = this;
return new scala.collection.immutable.StringOps(self);};
scala.String.prototype.matches = function(pattern) {
var self = this;
var regexp = new RegExp(pattern); return regexp.test(self);};
scala.String.prototype.replaceAll = function(toReplace, replaceWith) {
var self = this;

        var escapedToReplace = self.escapeRegExp(toReplace);
        var escapedReplaceWith = self.escapeRegExp(replaceWith);
        return self.replace(new RegExp(escapedToReplace, 'g'), escapedReplaceWith);
    };
scala.String.prototype.escapeRegExp = function(str) {
var self = this;
 return str.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\^\$\|\\]/g, "\\$&"); };
scala.String.prototype.__class__ = new s2js.runtime.client.core.Class('scala.String', []);
