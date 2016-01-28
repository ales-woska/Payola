s2js.runtime.client.core.get().classLoader.provide('scala.collection.Map');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.Iterable');
s2js.runtime.client.core.get().classLoader.require('scala.NoSuchElementException');
s2js.runtime.client.core.get().classLoader.require('scala.None');
s2js.runtime.client.core.get().classLoader.require('scala.Option');
s2js.runtime.client.core.get().classLoader.require('scala.Tuple2');
s2js.runtime.client.core.get().classLoader.require('scala.UnsupportedOperationException');
s2js.runtime.client.core.get().classLoader.require('scala.collection.mutable.ListBuffer');
scala.collection.Map = function() {
var self = this;
scala.collection.Iterable.apply(self, []);
self.internalJsObject = {};
};
s2js.runtime.client.core.get().inherit(scala.collection.Map, scala.collection.Iterable);
scala.collection.Map.prototype.foreach = function(f) {
var self = this;

        for (var key in self.internalJsObject) {
            if (self.internalJsObject.hasOwnProperty(key)) {
                f(new scala.Tuple2(key, self.internalJsObject[key]))
            }
        }
    };
scala.collection.Map.prototype.$plus$eq = function(x) {
var self = this;
self.internalJsObject[x._1] = x._2;};
scala.collection.Map.prototype.prepend = function(x) {
var self = this;
(function() {
throw new scala.UnsupportedOperationException('Can\'t prepend to a Map.', undefined);
})();
};
scala.collection.Map.prototype.reversed = function() {
var self = this;
return scala.collection.mutable.ListBuffer.get().empty().$plus$plus(self).reversed();
};
scala.collection.Map.prototype.$minus$eq = function(x) {
var self = this;
delete self.internalJsObject[x];};
scala.collection.Map.prototype.get = function(key) {
var self = this;

        if (s2js.runtime.client.core.get().isUndefined(self.internalJsObject[key])) {
            return scala.None.get();
        } else {
            return new scala.Some(self.internalJsObject[key]);
        }
    };
scala.collection.Map.prototype.getOrElse = function(key, $default) {
var self = this;
return (function($selector$1) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$1, 'scala.Some') && (true)) {
var v = $selector$1.productElement(0);
return v;
}
if ($selector$1 === scala.None.get()) {
return $default;
}
})(self.get(key));
};
scala.collection.Map.prototype.$apply = function(key) {
var self = this;
return (function($selector$2) {
if ($selector$2 === scala.None.get()) {
return self.$default(key);
}
if (s2js.runtime.client.core.get().isInstanceOf($selector$2, 'scala.Some') && (true)) {
var value = $selector$2.productElement(0);
return value;
}
})(self.get(key));
};
scala.collection.Map.prototype.contains = function(key) {
var self = this;
return self.get(key).isDefined();
};
scala.collection.Map.prototype.isDefinedAt = function(key) {
var self = this;
return self.contains(key);
};
scala.collection.Map.prototype.$default = function(key) {
var self = this;
return (function() {
throw new scala.NoSuchElementException(('key not found: ' + key), undefined);
})();
};
scala.collection.Map.prototype.put = function(key, value) {
var self = this;
var r = self.get(key);
self.update(key, value);
return r;

};
scala.collection.Map.prototype.update = function(key, value) {
var self = this;
self.$plus$eq(new scala.Tuple2(key, value));
};
scala.collection.Map.prototype.remove = function(key) {
var self = this;
var r = self.get(key);
self.$minus$eq(key);
return r;

};
scala.collection.Map.prototype.getOrElseUpdate = function(key, op) {
var self = this;
return (function($selector$3) {
if (s2js.runtime.client.core.get().isInstanceOf($selector$3, 'scala.Some') && (true)) {
var v = $selector$3.productElement(0);
return v;
}
if ($selector$3 === scala.None.get()) {
var d = op;
self.update(key, d);
return d;

}
})(self.get(key));
};
scala.collection.Map.prototype.values = function() {
var self = this;

        var result = scala.collection.mutable.ListBuffer.get().$apply();
        for (var key in self.internalJsObject) {
            if (key === 'length' || !widthRange.hasOwnProperty(key)) {
                continue;
            }
            result.$plus$plus$eq(widthRange[key]);
        }
        return result;
    };
scala.collection.Map.prototype.keys = function() {
var self = this;

        var result = scala.collection.mutable.ListBuffer.get().$apply();
        for (var key in self.internalJsObject) {
            if (key === 'length' || !self.internalJsObject.hasOwnProperty(key)) {
                continue;
            }
            result.$plus$eq(key);
        }
        return result;
    };
scala.collection.Map.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.Map', [scala.collection.Iterable]);
