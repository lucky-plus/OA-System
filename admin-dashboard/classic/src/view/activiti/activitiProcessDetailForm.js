/* 
* @Author: xgd
* @Date:   2017-10-22 22:07:22
* @Last Modified by:   xgd
* @Last Modified time: 2017-10-22 22:16:20
*/

Ext.define('Admin.view.activiti.activitiProcessDetailForm', {
    extend: 'Ext.form.Panel',
    alias: 'widget.activitiProcessDetailForm',
	
    requires: [
        'Ext.button.Button',
        'Ext.form.field.Text',
        'Ext.form.field.File',
		'Ext.window.Toast'
    ],

    controller: 'activitiProcessGridViewController',
    layout: {
        type:'vbox',
        align:'stretch'
    },

    bodyPadding: 10,
    scrollable: true,

    items: [
	{
		fieldLabel:'定义ID',
        xtype: 'textfield',
        name: 'id',
		disabled:false
    },
	{
		fieldLabel:'版本',
		xtype: 'numberfield',
		name: 'version',
		disabled:false
	},
	{
		fieldLabel:'关键字',
		xtype: 'textfield',
		name: 'key',
		disabled:false
	},
	{
		fieldLabel:'暂停状态',
		xtype: 'radiofield',
		name: 'suspended',
		disabled:false
	},
	{
		fieldLabel:'定义名字',
		xtype: 'textfield',
		name: 'name',
		disabled:false
	},
	{
		fieldLabel:'描述',
		xtype: 'textareafield',
		name: 'description',
		disabled:false
	}],
    bbar: {
        overflowHandler: 'menu',
        items: ['->',{
            xtype: 'button',
            text: 'Cancel',
            handler: 'closeWindow'
        }]
    }
});