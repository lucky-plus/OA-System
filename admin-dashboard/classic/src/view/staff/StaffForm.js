Ext.define('Admin.view.staff.StaffForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.staffForm',
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
	controller: 'StaffViewController',
    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    defaults: {
        labelWidth: 100,
        labelSeparator: ''
    },
    items: [{
		xtype: 'textfield',
		fieldLabel: 'userId',
		//allowBlank: false,
		name:'userId',

	},{
		xtype: 'textfield',
		fieldLabel: '姓名',
		name:'realName'
	},{
		xtype: 'datefield',
		format: 'Y/m/d H:i:s',
		fieldLabel: '入职时间',
		name:'onDutDate',
		value:new Date()
	},{
		xtype: 'combobox',
    	id: 'postChoice',
		fieldLabel: '职位',
		columns: 5,
		vertical: true,
		listeners: {
	      render: function () {
	        //通过extjs的ajax获取
	        Ext.Ajax.request({
	            url: 'post/findAll',
	            // 这里async 必须设置成false 否则页面加载时，无法将动态创建的checkBoxGroup添加到容器中
	            async : false,
	            success: function (response) {
	                var data = eval("(" + response.responseText + ")");
	                var len = data.length;//obj.data.length; "Table"这里的Table指的是后台返回 类似于data
	                if (data == null || len == 0) {
	                    return;
	                }

	                var radiogroup = Ext.getCmp("radiogroupOperation");
	                for (var i = 0; i < len; i++) {
	                    var radiobox = new Ext.form.Radio(
	                      {
	                          boxLabel: data[i].roleName,
	                          name: 'roleId',
	                          inputValue: data[i].roleId,
	                          checked: false
	                      });
	                    radiogroup.items.add(radiobox);
	                }
	            }
	        });
	      }
	    }
	},{
		xtype: 'textfield',
		fieldLabel: '联系电话',
		name:'mobilePhone'
    }],

	
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			ui:'soft-blue',
			//ui: 'soft-red',
			text: '保存',
			handler: 'staffGridFromSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'staffGridWindowsClose'
		}]
    }
});
