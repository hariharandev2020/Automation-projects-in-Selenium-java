import { Selector } from 'testcafe';
import { ClientFunction } from 'testcafe';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow();
    const autocompBtn   =  Selector('a').withExactText('Autocomplete');
    await t.expect(Selector(autocompBtn).exists).ok()
    .click(autocompBtn);

})

test('Autocomplete button check', async t => {
   
    const expected       = "BASIC";
    const iframe = Selector('.demo-frame', { timeout: 60000 });

    await t
           .switchToIframe(iframe)
           .typeText( Selector("#tags"),"a")
           .click(Selector(".ui-widget").find('ul>li').nth(3))
           .pressKey('enter');
    const actual = Selector("#tags").value;
           await t.expect(actual).eql( expected,"Iframe is not selected" );

});

test('Autocomplete button check-2', async t => {
   
    const iframe = Selector('.demo-frame', { timeout: 60000 });
        await t.switchToIframe(iframe)
           .typeText( Selector("#tags"),"script")
           await t.expect(Selector("#ui-id-1 > li").nth(0).exists).ok();
           await t.expect(Selector("#ui-id-1 > li").nth(1).exists).ok();
           await t.expect(Selector("#ui-id-1 > li").nth(2).exists).ok();
});
