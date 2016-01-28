s2js.runtime.client.core.get().classLoader.provide('scala.util.control.BreakControlException');
s2js.runtime.client.core.get().classLoader.provide('scala.util.control.Breaks');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Exception');
scala.util.control.BreakControlException = function() {
var self = this;
scala.Exception.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.util.control.BreakControlException, scala.Exception);
scala.util.control.BreakControlException.prototype.__class__ = new s2js.runtime.client.core.Class('scala.util.control.BreakControlException', [scala.Exception]);
s2js.runtime.client.core.get().mixIn(scala.util.control.Breaks, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.breakable = function(op) {
var self = this;
try {
op();
} catch ($ex$1) {
(function() {
if (s2js.runtime.client.core.get().isInstanceOf($ex$1, 'scala.util.control.BreakControlException')) {

return;
}
if (true) {
var ex = $ex$1;
(function() {
throw ex;
})();
return;
}
throw $ex$1;
})();
}

};
obj.$break = function() {
var self = this;
(function() {
throw new scala.util.control.BreakControlException();
})();
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.util.control.Breaks', []);
return obj;
}), true);
