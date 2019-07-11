/*
 * EH工具组件
 */
var EH = EH || {};
EH = {
	Dict: { //selectid: 选择框元素的id   nekey:数据库中字典的类别字段   selected: 默认值 , nevalue和comments都行   
		loadSelect: function (selectid, nekey, selected) {
			$.ajax({
				type: 'POST',
				url: 'backstage/baseDict/getDictSelect',
				data: {
					nekey: nekey
				},
				dataType: 'json',
				success: function(data) {
					if (data == null || data == "") {
						return;
					}
					var opts = "",
						name = "";
					opts = "<option value=''> </option>";
					$.each(data, function(n, v) {
						opts += "<option value='" + v.nevalue + "'";
						if (v.contents == selected || v.nevalue == selected) {
							opts += " selected='selected'"
						}
						opts += ">" + v.contents + "</option>";
					});
					$("#" + selectid).html(opts);
				}
			});
		}

	}
};