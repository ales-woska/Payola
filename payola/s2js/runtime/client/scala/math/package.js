s2js.runtime.client.core.get().classLoader.provide('scala.math');
s2js.runtime.client.core.get().mixIn(scala.math, new s2js.runtime.client.core.Lazy(function() {
var obj = {};
obj.E = Math.E;
obj.Pi = Math.PI;
obj.abs = function(x) {
var self = this;
return Math.abs(x);};
obj.acos = function(x) {
var self = this;
return Math.acos(x);};
obj.asin = function(x) {
var self = this;
return Math.asin(x);};
obj.atan = function(x) {
var self = this;
return Math.atan(x);};
obj.atan2 = function(x) {
var self = this;
return Math.atan2(x);};
obj.ceil = function(x) {
var self = this;
return Math.ceil(x);};
obj.cos = function(x) {
var self = this;
return Math.cos(x);};
obj.exp = function(x) {
var self = this;
return Math.exp(x);};
obj.floor = function(x) {
var self = this;
return Math.floor(x);};
obj.log = function(x) {
var self = this;
return Math.log(x);};
obj.max = function(x, y) {
var self = this;
return Math.max(x, y);};
obj.min = function(x, y) {
var self = this;
return Math.min(x, y);};
obj.pow = function(x, y) {
var self = this;
return Math.pow(x, y);};
obj.sqrt = function(x) {
var self = this;
return Math.sqrt(x);};
obj.random = function() {
var self = this;
return Math.random();};
obj.round = function(x) {
var self = this;
return Math.round(x);};
obj.sin = function(x) {
var self = this;
return Math.sin(x);};
obj.tan = function(x) {
var self = this;
return Math.tan(x);};
obj.signum = function(x) {
var self = this;
if(x > 0) { return 1; } else if(x == 0) { return 0; } else { return -1; }};
obj.__class__ = new s2js.runtime.client.core.Class('scala.math', []);
return obj;
}), true);
