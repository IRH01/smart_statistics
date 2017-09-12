function Map(){
		this.container = new Object();
	}
	Map.prototype.put = function(key, value){
		this.container[key] = value;
	};
	Map.prototype.get = function(key){
		return this.container[key];
	};
	Map.prototype.keySet = function() {
		var keyset = new Array();
		var count = 0;
		for (var key in this.container) {
		// 跳过object的extend函数
		if (key == 'extend') {
			continue;
		}
		keyset[count] = key;
			count++;
		}
		return keyset;
	};
	Map.prototype.size = function() {
		var count = 0;
		for (var key in this.container) {
		// 跳过object的extend函数
		if (key == 'extend'){
			continue;
		}
			count++;
		}
		return count;
	};
	Map.prototype.remove = function(key) {
		delete this.container[key];
	};
	Map.prototype.toString = function(){
		var str = "";
		for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
			str = str + +keys[i] + "-" + this.container[keys[i]] + "@";
		}
		str=str.substring(0,str.length-1);
		return str;
	};
	
	Map.prototype.count = function(){
		var count=0;
		for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
			count+= parseFloat(this.container[keys[i]]) ;
		}
		return count;
	};
	
	Map.prototype.change= function(value){
		var strs= new Array();  
		strs= value.split('@');
		for (var i=0;i<strs.length ;i++ ){ 
			var text= new Array();  
			text= strs[i].split('-');
			this.container[text[0]] = text[1];
	} 
	};