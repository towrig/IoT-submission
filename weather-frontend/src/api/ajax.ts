export const ajax = (url: string) => {
    return new Promise((resolve, reject) => {
        fetch(url).then((response) => {
            response.json().then(r => {
                resolve(r);
            }).catch(e => {
                reject({
                    message: "Failed to parse response.",
                    error: e,
                })
            })
        })
    })
}