import { Selector } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const radioBtn  = Selector('a').withExactText('Checkboxradio');
    await t.click(radioBtn);
})

test('Radio -1 test', async t => {
    page.browserscroll();
    const iframe    = Selector(".demo-frame"); 
        await t .switchToIframe(iframe)
        const r1 = Selector('input[type=radio]').nth(0);
        await t.expect(r1.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const r2 = Selector('input[type=radio]').nth(1);
        await t.expect(r2.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const r3 = Selector('input[type=radio]').nth(2);
        await t.expect(r3.hasAttribute('disabled')).notOk('ready for testing', { timeout: 5000 });
        const ex1 = "rgb(0, 127, 255";
        const ex2 = "rgb(246, 246, 246)";
        await t
        .click(Selector('label').withAttribute('for', 'radio-1'));
        await t
        .expect(ex1).ok(r1.getStyleProperty('backgroundcolor'))
        .expect(ex2).ok(r2.getStyleProperty('backgroundcolor'));
           
})