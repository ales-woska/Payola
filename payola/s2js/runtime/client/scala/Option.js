s2js.runtime.client.core.get().classLoader.provide('scala.None');
s2js.runtime.client.core.get().classLoader.provide('scala.Option');
s2js.runtime.client.core.get().classLoader.provide('scala.Some');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.Product');
s2js.runtime.client.core.get().classLoader.require('scala.IndexOutOfBoundsException');
s2js.runtime.client.core.get().classLoader.require('scala.NoSuchElementException');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.List');
s2js.runtime.client.core.get().classLoader.require('scala.collection.immutable.Nil');
scala.Option = function() {
var self = this;
scala.Product.apply(self, []);
};
s2js.runtime.client.core.get().inherit(scala.Option, scala.Product);
scala.Option.prototype.isDefined = function() {
var self = this;
return (! self.isEmpty());
};
scala.Option.prototype.getOrElse = function($default) {
var self = this;
return (function() {
if (self.isEmpty()) {
return $default;
} else {
return self.get();
}
})();
};
scala.Option.prototype.orNull = function(ev) {
var self = this;
return self.getOrElse(ev.$apply(null));
};
scala.Option.prototype.map = function(f) {
var self = this;
return (function() {
if (self.isEmpty()) {
return scala.None.get();
} else {
return new scala.Some(f(self.get()));
}
})();
};
scala.Option.prototype.flatMap = function(f) {
var self = this;
return (function() {
if (self.isEmpty()) {
return scala.None.get();
} else {
return f(self.get());
}
})();
};
scala.Option.prototype.flatten = function(ev) {
var self = this;
return (function() {
if (self.isEmpty()) {
return scala.None.get();
} else {
return ev.$apply(self.get());
}
})();
};
scala.Option.prototype.filter = function(p) {
var self = this;
return (function() {
if ((self.isEmpty() || p(self.get()))) {
return self;
} else {
return scala.None.get();
}
})();
};
scala.Option.prototype.filterNot = function(p) {
var self = this;
return (function() {
if ((self.isEmpty() || (! p(self.get())))) {
return self;
} else {
return scala.None.get();
}
})();
};
scala.Option.prototype.nonEmpty = function() {
var self = this;
return self.isDefined();
};
scala.Option.prototype.exists = function(p) {
var self = this;
return ((! self.isEmpty()) && p(self.get()));
};
scala.Option.prototype.foreach = function(f) {
var self = this;
if ((! self.isEmpty())) {
f(self.get());


}

};
scala.Option.prototype.collect = function(pf) {
var self = this;
return (function() {
if (((! self.isEmpty()) && pf.isDefinedAt(self.get()))) {
return new scala.Some(pf.$apply(self.get()));
} else {
return scala.None.get();
}
})();
};
scala.Option.prototype.orElse = function(alternative) {
var self = this;
return (function() {
if (self.isEmpty()) {
return alternative;
} else {
return self;
}
})();
};
scala.Option.prototype.toList = function() {
var self = this;
return (function() {
if (self.isEmpty()) {
return scala.collection.immutable.Nil.get();
} else {
return scala.collection.immutable.List.get().$apply(self.get());
}
})();
};
scala.Option.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Option', [scala.Product]);
scala.Some = function(x) {
var self = this;
self.x = x;
scala.Option.apply(self, []);
s2js.runtime.client.core.get().mixInFields(self, new scala.Product());
};
s2js.runtime.client.core.get().inherit(scala.Some, scala.Option);
s2js.runtime.client.core.get().mixInFunctions(scala.Some.prototype, scala.Product.prototype);
scala.Some.prototype.isEmpty = function() {
var self = this;
return false;
};
scala.Some.prototype.get = function() {
var self = this;
return self.x;
};
scala.Some.prototype.copy = function(x) {
var self = this;
if (typeof(x) === 'undefined') { x = self.x; }
return new scala.Some(x);
};
scala.Some.prototype.toString = function() {
var self = this;
return scala.runtime.ScalaRunTime.get()._toString(self);
};
scala.Some.prototype.productPrefix = function() {
var self = this;
return 'Some';
};
scala.Some.prototype.productArity = function() {
var self = this;
return 1;
};
scala.Some.prototype.productElement = function($x$1) {
var self = this;
return (function($selector$1) {
if ($selector$1 === 0) {
return self.x;
}
if (true) {
return (function() {
throw new scala.IndexOutOfBoundsException($x$1.toString());
})();
}
})($x$1);
};
scala.Some.prototype.__class__ = new s2js.runtime.client.core.Class('scala.Some', [scala.Option, scala.Product]);
s2js.runtime.client.core.get().mixIn(scala.None, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj = new scala.Option();
s2js.runtime.client.core.get().mixIn(obj, new scala.Product());
obj.isEmpty = function() {
var self = this;
return true;
};
obj.get = function() {
var self = this;
return (function() {
throw new scala.NoSuchElementException('None.get', undefined);
})();
};
obj.toString = function() {
var self = this;
return 'None';
};
obj.productPrefix = function() {
var self = this;
return 'None';
};
obj.productArity = function() {
var self = this;
return 0;
};
obj.productElement = function($x$1) {
var self = this;
return (function($selector$2) {
if (true) {
return (function() {
throw new scala.IndexOutOfBoundsException($x$1.toString());
})();
}
})($x$1);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.None', [scala.Option, scala.Product]);
return obj;
}), true);
s2js.runtime.client.core.get().mixIn(scala.Option, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.option2Iterable = function(xo) {
var self = this;
return (function() {
if (xo.isEmpty()) {
return scala.collection.immutable.Nil.get();
} else {
return scala.collection.immutable.List.get().$apply(xo.get());
}
})();
};
obj.$apply = function(x) {
var self = this;
return (function() {
if ((x == null)) {
return scala.None.get();
} else {
return new scala.Some(x);
}
})();
};
obj.empty = function() {
var self = this;
return scala.None.get();
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Option', []);
return obj;
}), true);
s2js.runtime.client.core.get().mixIn(scala.Some, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.toString = function() {
var self = this;
return 'Some';
};
obj.unapply = function(x$0) {
var self = this;
return (function() {
if ((x$0 == null)) {
return scala.None.get();
} else {
return new scala.Some(x$0.x);
}
})();
};
obj.$apply = function(x) {
var self = this;
return new scala.Some(x);
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.Some', []);
return obj;
}), true);
