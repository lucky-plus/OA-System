Ext.define('Admin.view.staff.StaffEditForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.staffEditForm',
	id:'staffEditForm',
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
		xtype: 'hidden',
		fieldLabel: 'userId',
		//allowBlank: false,
		name:'userId',

	},{
		xtype: 'textfield',
		fieldLabel: '姓名',
		name:'realName',
		disabled:true
	},{
		xtype: 'combobox',
		id: 'deptComBoBox',
		name:'deptId',
		fieldLabel: '部门',
		store : 
		new Ext.data.Store( {
			proxy : new Ext.data.HttpProxy( {
						url : 'dept/findDepts'//提交到某action的某方法
				  }),
			reader : {type:'json'},//需要显示的数据实体字段
			autoLoad : true
			}),
		queryMode: 	  'local',
		displayField: 'deptName',
		valueField:   'deptId',
		listConfig : {//设置下拉时显示的样式
			maxHeight : 200,//下拉时最大高度
		},
		listeners: {// select监听函数
            select: function(){  				
				var dept = Ext.getCmp('deptComBoBox');
                // Ext.getCmp('postComBoBox').reset();
				// Ext.Msg.alert("",dept.getValue());
				// Ext.Ajax.request({
					// url : "post/findPostsByDeptId",
					// params : {
						// deptId : dept.getValue()
					// },
					// success: function(){
						
					// }
				// });
				 //Ext.getCmp('postComBoBox').getStore().removeAll();
				
				Ext.getCmp('postComBoBox').getStore().load({params:{deptId : dept.getValue()}});
				
				// Ext.getCmp('postComBoBox').reset();
				}    
			}
		},{
			xtype: 'combobox',
			id: 'postComBoBox',
			queryMode: 'remote',
			name:'postId',
			async : false,
			fieldLabel: '职位',
			store : new Ext.data.Store( {
					proxy : new Ext.data.HttpProxy( {
								url : 'post/findPostsByDeptId',//提交到某action的某方法
						  }),
					reader : {type:'json'},//需要显示的数据实体字段
					autoLoad : true
				}),
			queryMode: 	  'local',
			displayField: 'postName',
			valueField:   'postId'
		}],

	
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
			xtype: 'button',
			ui:'soft-blue',
			//ui: 'soft-red',
			text: '保存',
			handler: 'staffGridEditSubmit'
		},{
			xtype: 'button',
			//ui: 'gray',
			text: '取消',
			handler: 'staffGridWindowsClose'
		}]
    }
});