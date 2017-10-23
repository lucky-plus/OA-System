Ext.define('Admin.view.profile.FileUploadFormWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.fileUploadFormWindow',

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

	autoShow: true,
	modal: true,
	layout: 'fit',
	controller: 'profileViewController',
	view:'fileUploadContainer',



	afterRender: function () {
	    var me = this;
	    me.callParent(arguments);
	    me.syncSize();
	    Ext.on(me.resizeListeners = {
	        resize: me.onViewportResize,
	        scope: me,
	        buffer: 50
	    });
	},
	doDestroy: function () {
	    Ext.un(this.resizeListeners);
	    this.callParent();
	},
	onViewportResize: function () {
	    this.syncSize();
	},
	syncSize: function () {
        var width = Ext.Element.getViewportWidth(),
            height = Ext.Element.getViewportHeight();
        this.setSize(Math.floor(width * 0.5), Math.floor(height * 0.5));
        this.setXY([ Math.floor(width * 0.05), Math.floor(height * 0.05) ]);
    }
});
