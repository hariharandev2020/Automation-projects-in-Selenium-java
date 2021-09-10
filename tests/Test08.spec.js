import { Selector } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const dialogbox  = Selector('a').withExactText('Dialog');
    await t.click(dialogbox);
})
const dialog = Selector('div[role=dialog]');
const header = Selector('span#ui-id-1');
const text   = Selector('#dialog p');
const btn    = Selector('button');

test("Dialogue box Enable check", async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    .expect(dialog.hasAttribute('disabled')).notOk();
})
test("Dialogue box Text check", async t => {
     page.browserscroll();
     await t.switchToIframe('.demo-frame')
    await t.expect(await header.innerText).eql('Basic dialog')
    await t.expect(await text.innerText).eql("This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.")
})
test('Drag the element', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    .drag(dialog, 360, 0, {
        offsetX: 10,
        offsetY: 10,
        modifiers: {
            shift: true
        }
    });
    const lf =await dialog.getStyleProperty('left');
    await t.expect(lf).eql("279px");
})
test('Close the dialog', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    .click(btn)
    .expect(dialog.visible).notOk();
})
