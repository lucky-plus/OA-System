Ext.define('Admin.view.authority.AuthorityGridWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.authorityGridWindow',
    autoShow: true,
    modal: true,
    layout: 'fit',
    //width: 200,
    //height: 200,
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
		//根据window内容设置宽高百分比
        this.setSize(Math.floor(width * 0.45), Math.floor(height * 0.3));
        this.setXY([ Math.floor(width * 0.05), Math.floor(height * 0.05) ]);
    }
});
