s2js.runtime.client.core.get().classLoader.provide('scala.collection.immutable.List');
s2js.runtime.client.core.get().classLoader.provide('scala.collection.immutable.Nil');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.SeqCompanion');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.immutable.Seq');
s2js.runtime.client.core.get().classLoader.require('scala.NoSuchElementException');
s2js.runtime.client.core.get().classLoader.require('scala.UnsupportedOperationException');
scala.collection.immutable.List = function() {
var self = this;
scala.collection.immutable.Seq.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.collection.immutable.List, scala.collection.immutable.Seq);
scala.collection.immutable.List.prototype.newInstance = function() {
var self = this;
return scala.collection.immutable.List.get().empty();
};
scala.collection.immutable.List.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.List', [scala.collection.immutable.Seq]);
s2js.runtime.client.core.get().mixIn(scala.collection.immutable.List, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj = new scala.collection.SeqCompanion();
obj.empty = function() {
var self = this;
return new scala.collection.immutable.List();
};
obj.$apply = function() {
var self = this;
var xs = scala.collection.immutable.List.get().fromJsArray([].splice.call(arguments, 0, arguments.length - 0));
return self.fromJsArray(xs.getInternalJsArray());};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.List', [scala.collection.SeqCompanion]);
return obj;
}), true);
s2js.runtime.client.core.get().mixIn(scala.collection.immutable.Nil, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj = new scala.collection.immutable.List();
obj.isEmpty = function() {
var self = this;
return true;
};
obj.head = function() {
var self = this;
return (function() {
throw new scala.NoSuchElementException('head of empty list', undefined);
})();
};
obj.tail = function() {
var self = this;
return (function() {
throw new scala.UnsupportedOperationException('tail of empty list', undefined);
})();
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.immutable.Nil', [scala.collection.immutable.List]);
return obj;
}), true);
