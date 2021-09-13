import { Selector,ClientFunction  } from 'testcafe';
import page from './method.po';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow()
    page.browserscroll();
    const dialogbox  = Selector('a').withExactText('Dialog');
    await t.click(dialogbox);
})
test("Dialogue box Enable check", async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const dialog = Selector('div[role=dialog]');
    await t.expect(dialog.hasAttribute('disabled')).notOk();
})
test("Dialogue box Text check", async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const header = Selector('span#ui-id-1');
    const text   = Selector('#dialog p');
    await t.expect(await header.innerText).eql('Basic dialog')
    await t.expect(await text.innerText).eql("This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.")
})
test('Drag the element', async t => {
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const dialog = Selector('div[role=dialog]');
    await t.drag(dialog, 360, 0);
    const lf =await dialog.getStyleProperty('left');
    await t.expect(lf).eql("139.5px");
})
test.skip('Resize the dialog box', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame')
    const changeHammerheadDiv = ClientFunction(() => {
    const hammerHeadDiv = document.querySelector('body div:nth-child(1)');
    hammerHeadDiv.style.width =  '900';
});
// await t.switchToIframe('.demo-frame');
await changeHammerheadDiv();
wait(2000)
const width = dialog.getStyleProperty('width');
console.log(await dialog.width);
})
test('Close the dialog', async t =>{
    page.browserscroll();
    await t.switchToIframe('.demo-frame');
    const dialog = Selector('div[role=dialog]');
    const btn    = Selector('button');
    await t.click(btn)
    .expect(dialog.visible).notOk();
})

