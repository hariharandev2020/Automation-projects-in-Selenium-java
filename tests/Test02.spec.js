import { Selector } from 'testcafe';
import { ClientFunction } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    const Btn   =  Selector('a').withExactText('Button');
    await t.click(Btn);
})

test('Button test', async t => {
    await t.switchToIframe('.demo-frame')
    const btn = Selector('.widget').find('.button.ui-button')
    // console.log(btn)
    // await t.click(btn, { modifiers: { shift: true } })

    var ctrlClickRow = ClientFunction((row) => {
        $(btn).trigger($.Event("click", { ctrlKey: true }));
    });
    await t.expect(btn.getStyleProperty('background-color')).match('rgba(0 127 255 1)');
    
})

