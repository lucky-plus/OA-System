Ext.define('Admin.view.role.RoleGridForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.roleGridForm',
	id:'roleGridForm',//Ext.getCmp('roleGridForm');
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File',
        'Ext.form.field.HtmlEditor',
		'Ext.form.field.TextArea',
		'Ext.form.field.Time',
		'Ext.form.field.ComboBox',
		'Ext.form.field.Date',
		'Ext.form.field.Radio',
		'Ext.form.field.Hidden'
    ],
    //viewModel: {type: 'emailcompose'},
    //cls: 'email-compose',
	controller: 'roleViewController',
    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    defaults: {
        labelWidth: 60,
        labelSeparator: ''
    },
    items: [{
		xtype: 'hidden',
		fieldLabel: 'Id',
		//allowBlank: false,
		name:'roleId'
	},{
		xtype: 'textfield',
		fieldLabel: '角色名称',
		name:'roleName'
	},{
		xtype: 'textfield',
		fieldLabel: '角色等级',
		name:'roleLevel'
	},
	{
		xtype: 'checkboxgroup',
    	id: 'checkboxgroupOperation',
		fieldLabel: '用户权限',
		columns: 4,
		vertical: true,
		// items: [
		// 	{ boxLabel: '1',   name: 'moduleIds', inputValue: '1' },
		// 	{ boxLabel: '1-1', name: 'moduleIds', inputValue: '2' },
		// 	{ boxLabel: '1-2', name: 'moduleIds', inputValue: '3' },
		// 	{ boxLabel: '2',   name: 'moduleIds', inputValue: '4' },
		// 	{ boxLabel: '2-1', name: 'moduleIds', inputValue: '5' },
		// 	{ boxLabel: '2-2', name: 'moduleIds', inputValue: '6' },
		// 	{ boxLabel: '2-3', name: 'moduleIds', inputValue: '7' },
		// 	{ boxLabel: '3',   name: 'moduleIds', inputValue: '8' },
		// 	{ boxLabel: '3-1', name: 'moduleIds', inputValue: '9' },
		// 	{ boxLabel: '3-2', name: 'moduleIds', inputValue: '10' }
		// ]
		listeners: {
	      render: function () {
	        //通过extjs的ajax获取
	        Ext.Ajax.request({
	            url: 'module/findAll.json',
	            // 这里async 必须设置成false 否则页面加载时，无法将动态创建的checkBoxGroup添加到容器中
	            async : false,
	            success: function (response) {
	                var data = eval("(" + response.responseText + ")");
	                var len = data.length;//obj.data.length; "Table"这里的Table指的是后台返回 类似于data
	                if (data == null || len == 0) {
	                    return;
	                }

	                var checkboxgroup = Ext.getCmp("checkboxgroupOperation");
	                for (var i = 0; i < len; i++) {
	                    var checkbox = new Ext.form.Checkbox(
	                      {
	                          boxLabel: data[i].modelName,
	                          name: 'moduleIds',
	                          inputValue: data[i].moduleId,
	                          checked: false
	                      });
	                    checkboxgroup.items.add(checkbox);
	                }
	                //重新调整版面布局
	                // var form = Ext.getCmp('roleGridForm');
                 	// form.reload();
	            }
	        });
	      }
	    }
	}
	],
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			//ui: 'soft-red',
			text: '提交',
			handler: 'roleGridFormSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'roleGridWindowClose'
		}]
    }
});