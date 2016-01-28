s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.client.core');
s2js.runtime.client.core.get().classLoader.require('s2js.runtime.client.core.ClassLoader');
s2js.runtime.client.core.get().classLoader.require('scala.ClassCastException');
s2js.runtime.client.core.get().mixIn(s2js.runtime.client.core, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.classLoader = new s2js.runtime.client.core.ClassLoader();
obj.isUndefined = function(obj) {
var self = this;
return typeof obj === 'undefined';};
obj.isDefined = function(obj) {
var self = this;
return (! s2js.runtime.client.core.get().isUndefined(obj));
};
obj.isObject = function(obj) {
var self = this;
return obj === Object(obj);};
obj.isArray = function(obj) {
var self = this;
return (s2js.runtime.client.core.get().nativeToString(obj) == '[object Array]');
};
obj.isFunction = function(obj) {
var self = this;
return (s2js.runtime.client.core.get().nativeToString(obj) == '[object Function]');
};
obj.isString = function(obj) {
var self = this;
return (s2js.runtime.client.core.get().nativeToString(obj) == '[object String]');
};
obj.isNumber = function(obj) {
var self = this;
return (s2js.runtime.client.core.get().nativeToString(obj) == '[object Number]');
};
obj.isBoolean = function(obj) {
var self = this;
return obj === true || obj === false || self.nativeToString(obj) == '[object Boolean]';};
obj.isInteger = function(obj) {
var self = this;
return self.isNumber(obj) && (obj % 1 === 0);};
obj.isChar = function(obj) {
var self = this;
return self.isString(obj) && obj.length === 1;};
obj.inherit = function(subClass, superClass) {
var self = this;

            function temporaryConstructor() {};
            temporaryConstructor.prototype = superClass.prototype;
            subClass.prototype = new temporaryConstructor();
            subClass.prototype.constructor = subClass;
        };
obj.mixInFields = function(targetObject, sourceObject) {
var self = this;

            for (var i in sourceObject) {
                if (!self.isFunction(sourceObject[i]) &&
                    !Object.prototype.hasOwnProperty.call(targetObject, i) &&
                    !Object.prototype.hasOwnProperty.call(targetObject.constructor.prototype, i)) {
                    targetObject[i] = sourceObject[i];
                }
            }
        };
obj.mixInFunctions = function(targetObject, sourceObject) {
var self = this;

            for (var i in sourceObject) {
                if (self.isFunction(sourceObject[i]) &&
                    !Object.prototype.hasOwnProperty.call(targetObject.constructor.prototype, i)) {
                    targetObject[i] = sourceObject[i];
                }
            }
        };
obj.mixIn = function(targetObject, sourceObject) {
var self = this;
s2js.runtime.client.core.get().mixInFields(targetObject, sourceObject);
s2js.runtime.client.core.get().mixInFunctions(targetObject, sourceObject);

};
obj.isObjectDefined = function(path) {
var self = this;
return s2js.runtime.client.core.get().isDefined(eval(path));
};
obj.declareObject = function(path) {
var self = this;

            var parentPath = window;
            while (path != '') {
                var index = path.indexOf('.');
                var name = '';
                if (index >= 0) {
                    name = path.substring(0, index);
                    path = path.substring(index + 1);
                } else {
                    name = path;
                    path = '';
                }

                if (self.isUndefined(parentPath[name])) {
                    parentPath[name] = {};
                }
                parentPath = parentPath[name];
            }
        };
obj.classOf = function(obj) {
var self = this;

            if (self.isDefined(obj.__class__)) {
                return obj.__class__;
            }
            return null;
        };
obj.isInstanceOf = function(obj, className) {
var self = this;
var classNameIsAny = (className == 'scala.Any');
var classNameIsAnyOrAnyVal = (classNameIsAny || (className == 'scala.AnyVal'));
var classNameIsAnyOrAnyRef = (classNameIsAny || (className == 'scala.AnyRef'));
return (function() {
if ((s2js.runtime.client.core.get().isUndefined(obj) || (obj == null))) {
return false;
} else {
return (function() {
if (s2js.runtime.client.core.get().isNumber(obj)) {
return (function($selector$1) {
if (($selector$1 === 'scala.Byte') || ($selector$1 === 'scala.Short') || ($selector$1 === 'scala.Int') || ($selector$1 === 'scala.Long')) {
return s2js.runtime.client.core.get().isInteger(obj);
}
if (($selector$1 === 'scala.Float') || ($selector$1 === 'scala.Double')) {
return true;
}
if (true) {
return classNameIsAnyOrAnyVal;
}
})(className);
} else {
return (function() {
if (s2js.runtime.client.core.get().isBoolean(obj)) {
return (classNameIsAnyOrAnyVal || (className == 'scala.Boolean'));
} else {
return (function() {
if (s2js.runtime.client.core.get().isString(obj)) {
return (function($selector$2) {
if ($selector$2 === 'scala.Char') {
return s2js.runtime.client.core.get().isChar(obj);
}
if ($selector$2 === 'scala.String') {
return true;
}
if (true) {
return classNameIsAnyOrAnyRef;
}
})(className);
} else {
return (function() {
if ((s2js.runtime.client.core.get().isObject(obj) && classNameIsAnyOrAnyRef)) {
return true;
} else {
var objClass = s2js.runtime.client.core.get().classOf(obj);
return ((objClass != null) && s2js.runtime.client.core.get().classIsSubClassOrEqual(objClass, className));

}
})();
}
})();
}
})();
}
})();
}
})();

};
obj.classIsSubClassOrEqual = function(c, classFullName) {
var self = this;

            if (c.fullName === classFullName) {
                return true;
            }
            for (var i in c.parentClasses) {
                var parentClass = self.classOf(c.parentClasses[i].prototype)
                if (parentClass !== null && self.classIsSubClassOrEqual(parentClass, classFullName)) {
                    return true;
                }
            }
            return false;
        };
obj.asInstanceOf = function(obj, className) {
var self = this;
if ((! s2js.runtime.client.core.get().isInstanceOf(obj, className))) {
(function() {
throw new scala.ClassCastException((((('The object \'' + obj.toString()) + '\' can\'t be casted to ') + className) + ''));
})();
}

return obj;

};
obj.nativeToString = function(obj) {
var self = this;
return Object.prototype.toString.call(obj)};
obj.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.client.core', []);
return obj;
}), true);
