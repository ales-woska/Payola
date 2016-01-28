s2js.runtime.client.core.get().classLoader.provide('scala.Double');
s2js.runtime.client.core.get().mixIn(scala.Double, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.MinPositiveValue = Number.MIN_VALUE;
obj.NaN = 0 / 0;
obj.PositiveInfinity = Number.POSITIVE_INFINITY;
obj.NegativeInfinity = Number.NEGATIVE_INFINITY;
obj.MinValue = - Number.MAX_VALUE;
obj.MaxValue = Number.MAX_VALUE;
obj.__class__ = new s2js.runtime.client.core.Class('scala.Double', []);
return obj;
}), true);
