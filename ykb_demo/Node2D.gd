extends Node2D

var plugin
# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.

func _on_init_pressed():
	plugin = Engine.get_singleton("Hykb")
	$sdk.text = "sdk_plugin_object:"+str(plugin)
	if plugin != null:
		#从这里传入初始化参数
		plugin.init(10690)
