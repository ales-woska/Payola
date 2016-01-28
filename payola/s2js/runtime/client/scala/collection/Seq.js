s2js.runtime.client.core.get().classLoader.provide('scala.collection.Seq');
s2js.runtime.client.core.get().classLoader.declarationRequire('scala.collection.Iterable');
s2js.runtime.client.core.get().classLoader.require('scala.util.control.Breaks');
scala.collection.Seq = function() {
var self = this;
scala.collection.Iterable.apply(self, []);
self.internalJsArray = [];
};
s2js.runtime.client.core.get().inherit(scala.collection.Seq, scala.collection.Iterable);
scala.collection.Seq.prototype.getInternalJsArray = function() {
var self = this;
return self.internalJsArray;
};
scala.collection.Seq.prototype.setInternalJsArray = function(value) {
var self = this;
self.internalJsArray = value;
};
scala.collection.Seq.prototype.distinct = function() {
var self = this;

        var u = {}, a = [];
        for(var i = 0, l = self.getInternalJsArray().length; i < l; ++i){
            if(u.hasOwnProperty(self.getInternalJsArray()[i])) {
                continue;
            }
            a.push(self.getInternalJsArray()[i]);
            u[self.getInternalJsArray()[i]] = 1;
        }
        return a;
                };
scala.collection.Seq.prototype.sortWith = function(f) {
var self = this;

        return self.getInternalJsArray().sort(function(a,b){
            if (f(a,b)){ return -1; }
            return 1;
        });
                };
scala.collection.Seq.prototype.foreach = function(f) {
var self = this;

        for (var i in self.getInternalJsArray()) {
            f(self.getInternalJsArray()[i]);
        }
    };
scala.collection.Seq.prototype.append = function(x) {
var self = this;
self.getInternalJsArray().push(x);};
scala.collection.Seq.prototype.$plus$eq = function(x) {
var self = this;
self.append(x);
};
scala.collection.Seq.prototype.$colon$plus = function(x) {
var self = this;
self.append(x);
};
scala.collection.Seq.prototype.reversed = function() {
var self = this;
var elems = self.newInstance();
self.foreach(function(x) {
elems.prepend(x);
});
return elems;

};
scala.collection.Seq.prototype.size = function() {
var self = this;
return self.getInternalJsArray().length;};
scala.collection.Seq.prototype.$apply = function(n) {
var self = this;

        if (s2js.runtime.client.core.get().isUndefined(self.getInternalJsArray()[n])) {
            throw new scala.NoSuchElementException('An item with index ' + n + ' is not present.');
        }
        return self.getInternalJsArray()[n];
    };
scala.collection.Seq.prototype.update = function(n, newelem) {
var self = this;

        if (self.size() <= n) {
            throw new scala.NoSuchElementException('An item with index ' + n + ' is not present.');
        }
        self.getInternalJsArray()[n] = newelem;
    };
scala.collection.Seq.prototype.$length = function() {
var self = this;
return self.size();
};
scala.collection.Seq.prototype.remove = function(index) {
var self = this;

        if (index < 0 || self.size() <= index) {
            throw new scala.NoSuchElementException('An item with index ' + n + ' is not present.');
        }
        var removed = self.getInternalJsArray()[index];
        self.getInternalJsArray().splice(index, 1);
        return removed;
    };
scala.collection.Seq.prototype.prepend = function(x) {
var self = this;
self.getInternalJsArray().splice(0, 0, x);};
scala.collection.Seq.prototype.insert = function(n, x) {
var self = this;
self.getInternalJsArray().splice(n, 0, x);};
scala.collection.Seq.prototype.$minus$eq = function(x) {
var self = this;

        var index = self.getInternalJsArray().indexOf(x);
        if (index != -1) {
            self.getInternalJsArray().splice(index, 1);
        }
    };
scala.collection.Seq.prototype.clone = function() {
var self = this;
return self.getInternalJsArray().slice(0);};
scala.collection.Seq.prototype.indexWhere = function(p, from) {
var self = this;
if (typeof(from) === 'undefined') { from = 0; }
var i = from;
scala.util.control.Breaks.get().breakable(function() {
self.drop(from).foreach(function(x) {
if (p(x)) {
scala.util.control.Breaks.get().$break();
} else {
i = (i + 1);
}

});
i = -1;

});
return i;

};
scala.collection.Seq.prototype.$indexOf = function(item) {
var self = this;
return self.indexWhere(function($x$1) {
return ($x$1 == item);
}, undefined);
};
scala.collection.Seq.prototype.contains = function(x) {
var self = this;
return self.exists(function($x$2) {
return ($x$2 == x);
});
};
scala.collection.Seq.prototype.startsWith = function(prefix) {
var self = this;
return (function($selector$1) {
if (true) {
var prefixLength = $selector$1;
if ((prefixLength > self.$length())) {
return false;
}
}
if ($selector$1 === 0) {
return true;
}
if (true) {
var prefixLength = $selector$1;
var result = true;
scala.util.control.Breaks.get().breakable(function() {
var index = 0;
prefix.foreach(function(item) {
if ((item != self.$apply(index))) {
result = false;
scala.util.control.Breaks.get().$break();

}

index = (index + 1);

});

});
return result;

}
})(prefix.$length());
};
scala.collection.Seq.prototype.endsWith = function(suffix) {
var self = this;
return (function($selector$2) {
if (true) {
var suffixLength = $selector$2;
if ((suffixLength > self.$length())) {
return false;
}
}
if ($selector$2 === 0) {
return true;
}
if (true) {
var suffixLength = $selector$2;
var result = true;
scala.util.control.Breaks.get().breakable(function() {
var startIndex = (self.$length() - suffixLength);
var index = 0;
suffix.foreach(function(item) {
if ((item != self.$apply((startIndex + index)))) {
result = false;
scala.util.control.Breaks.get().$break();

}

index = (index + 1);

});

});
return result;

}
})(suffix.$length());
};
scala.collection.Seq.prototype.toArray = function() {
var self = this;
return self.getInternalJsArray();
};
scala.collection.Seq.prototype.toList = function() {
var self = this;
return self.getInternalJsArray();
};
scala.collection.Seq.prototype.toBuffer = function() {
var self = this;
return self.getInternalJsArray();
};
scala.collection.Seq.prototype.__class__ = new s2js.runtime.client.core.Class('scala.collection.Seq', [scala.collection.Iterable]);
s2js.runtime.client.core.get().mixIn(scala.collection.Seq, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.canBuildFrom = function() {
var self = this;
return true;
};
obj.__class__ = new s2js.runtime.client.core.Class('scala.collection.Seq', []);
return obj;
}), true);
