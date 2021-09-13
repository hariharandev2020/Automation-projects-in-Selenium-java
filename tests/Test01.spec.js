import { Selector } from 'testcafe';

fixture `Test JQuery UI page`
.page `https://jqueryui.com/`

.beforeEach(async t => {
    await t.maximizeWindow();
})

test('Accordion Section-2 Test',async t => {
    const accordion   =  Selector('a').withExactText('Accordion');
    const section2 = Selector('#ui-id-3');
    const content2 = Selector('#ui-id-4');
    await t.expect(accordion.exists).ok();
    await t.click(accordion);
    await t.switchToIframe('.demo-frame');
    await t.click(section2)
    .expect(section2.getStyleProperty('background-color')).contains('rgb(0, 127, 255)')
    .expect(content2.getStyleProperty('display')).eql('block')
    .expect(section2.innerText).eql('Section 2')
    .expect(content2.innerText).eql('Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.')
})
test('Accordion Section-3 Test',async t => {
    const accordion   =  Selector('a').withExactText('Accordion');
    const section3 = Selector('#ui-id-5');
    const content3 = Selector('#ui-id-6');
    await t.expect(accordion.exists).ok();
    await t.click(accordion);
    await t.switchToIframe('.demo-frame');
    await t.click(section3)
    .expect(section3.getStyleProperty('background-color')).contains('rgb(0, 127, 255)')
    .expect(content3.getStyleProperty('display')).eql('block')
    .expect(section3.innerText).eql('Section 3')
    .expect(content3.innerText).contains('Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.')
    .expect(Selector("#ui-id-6 > ul>li").count).eql(3);
})

test('Accordion Section-1 Test',async t => {
    const accordion   =  Selector('a').withExactText('Accordion');
    await t.expect(accordion.exists).ok();
    await t.click(accordion);
    await t.switchToIframe('.demo-frame');
    const section4 = Selector('#ui-id-7');
    await t.click(section4)
    await t.expect(section4.getStyleProperty('background-color')).contains('rgb(0, 127, 255)')
    await t.expect(section4.innerText).eql('Section 4')
    const content4 = Selector('#ui-id-8>p:nth-child(1)');
    await t.expect(await content4.innerText).contains('Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est.')
    const content4_2 = Selector('#ui-id-8>p:nth-child(2)');
    await t.expect(await content4_2.innerText).contains('Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.')
})
test('Accordion Section-4Test',async t => {
    const accordion   =  Selector('a').withExactText('Accordion');
    const section1 = Selector('#ui-id-1');
    const content1 = Selector('#ui-id-2');
    await t.expect(accordion.exists).ok();
    await t.click(accordion);
    await t.switchToIframe('.demo-frame');
    await t.click(section1)
    .expect(section1.getStyleProperty('background-color')).contains('rgb(0, 127, 255)')
    .expect(content1.getStyleProperty('display')).eql('block')
    .expect(section1.innerText).eql('Section 1')
    .expect(content1.innerText).contains('Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.')
    
})
test('Input visiblity and label test', async t  => {
    const autocompBtn   =  Selector('a').withExactText('Autocomplete');
    await t.expect(Selector(autocompBtn).exists).ok()
    .click(autocompBtn);
    await t.switchToIframe('.demo-frame');
    const tag = Selector('label[for=tags]');
    await t.expect(await tag.innerText).eql('Tags: ');
})
test('Autocomplete button check', async t => {
    const autocompBtn   =  Selector('a').withExactText('Autocomplete');
    await t.expect(Selector(autocompBtn).exists).ok()
    .click(autocompBtn);
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
    const autocompBtn   =  Selector('a').withExactText('Autocomplete');
    await t.expect(Selector(autocompBtn).exists).ok()
    .click(autocompBtn);
    const iframe = Selector('.demo-frame', { timeout: 60000 });
        await t.switchToIframe(iframe)
           .typeText( Selector("#tags"),"script")
           await t.expect(Selector("#ui-id-1 > li").nth(0).exists).ok();
           await t.expect(Selector("#ui-id-1 > li").nth(1).exists).ok();
           await t.expect(Selector("#ui-id-1 > li").nth(2).exists).ok();
});

