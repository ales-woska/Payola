s2js.runtime.client.core.get().classLoader.provide('s2js.runtime.shared.DependencyPackage');
s2js.runtime.shared.DependencyPackage = function(javaScript, css, providedSymbols) {
var self = this;
self.javaScript = javaScript;
self.css = css;
self.providedSymbols = providedSymbols;
};
s2js.runtime.shared.DependencyPackage.prototype.__class__ = new s2js.runtime.client.core.Class('s2js.runtime.shared.DependencyPackage', []);
